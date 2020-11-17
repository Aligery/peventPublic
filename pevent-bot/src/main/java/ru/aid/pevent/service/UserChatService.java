package ru.aid.pevent.service;

import org.apache.ibatis.session.SqlSession;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.aid.pevent.constants.SecurityConstants;
import ru.aid.pevent.model.entity.chat.ChatPevent;
import ru.aid.pevent.model.entity.chat.ChatUserSequence;
import ru.aid.pevent.model.entity.user.PeventUser;
import ru.aid.pevent.mybatis.SqlSessionResourceManager;
import ru.aid.pevent.mybatis.mapper.ChatsMapper;
import ru.aid.pevent.mybatis.mapper.UsersMapper;

import java.util.List;

public class UserChatService {

    private UserChatService(){}


    public static void handleTelegramsIdToPeventDB(Update update) {
        Chat chat = update.getMessage().getChat();

        //Удалить все привязки если бота удалили
        if (update.getMessage().getLeftChatMember() != null &&
                update.getMessage().getLeftChatMember().getBot()) {
            deleteChatSequence(chat);
            return;
        }
        //в зависимости от типа чата либо добавляем нового пользователя, либо привязки в чате
        if (chat.getId()>0) {
            insertIntoUserTable(chat);
        } else if (isBotAdded(update.getMessage().getNewChatMembers())) {
            insertIntoChatTables(chat, update.getMessage().getFrom().getId());
        }
    }


    private static void insertIntoUserTable(Chat chat) {
        try (SqlSession sqlSession = SqlSessionResourceManager.getInstance().openNewSession()) {
            UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
            PeventUser peventUser = new PeventUser();
            peventUser.setFirstName(chat.getFirstName());
            peventUser.setLastName(chat.getLastName());
            peventUser.setTelegramUserId(chat.getId());
            peventUser.setDeleted(false);

            usersMapper.insertNewUser(peventUser);
            sqlSession.commit();
        }
    }

    private static void insertIntoChatTables(Chat chat, long telegramUserId) {
        try (SqlSession sqlSession = SqlSessionResourceManager.getInstance().openNewSession()) {
            ChatsMapper chatsMapper = sqlSession.getMapper(ChatsMapper.class);
            ChatPevent chatPevent = new ChatPevent();
            chatPevent.setChatName(chat.getTitle());
            chatPevent.setTelegramChatId(chat.getId());
            chatPevent.setDeleted(false);

            chatsMapper.insertNewChat(chatPevent);

            ChatUserSequence chatUserSequence = new ChatUserSequence();
            chatUserSequence.setTelegramChatId(chat.getId());
            chatUserSequence.setTelegramUserId(telegramUserId);
            chatUserSequence.setDeleted(false);
            chatUserSequence.setLeader(true);


            chatsMapper.insertChatUserSequence(chatUserSequence);

            sqlSession.commit();
        }
    }

    private static void deleteChatSequence(Chat chat) {
        try (SqlSession session = SqlSessionResourceManager.getInstance().openNewSession()) {
            ChatsMapper chatsMapper = session.getMapper(ChatsMapper.class);
            chatsMapper.deleteChatUsersSequence(chat.getId());
            chatsMapper.deleteChat(chat.getId());
            session.commit();
        }
    }

    private static boolean isBotAdded(List<User> users) {
        boolean isBot = false;
        for (User user : users) {
            if (user.getBot() && user.getFirstName().equalsIgnoreCase(SecurityConstants.BOT_NAME)) {
                isBot = true;
            }
        }
        return isBot;
    }

}
