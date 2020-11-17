package ru.aid.pevent.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import ru.aid.pevent.model.entity.chat.ChatPevent;
import ru.aid.pevent.model.entity.chat.ChatUserSequence;

import java.util.List;

public interface ChatsMapper {

    void insertNewChat(@Param("obj") ChatPevent chatPevent);

    void deleteChat(long telegramChatId);

    void deleteChatUsersSequence(long telegramChatId);

    void insertChatUserSequence(@Param("obj") ChatUserSequence sequence);

    List<ChatPevent> getChatsByTelegramUserId(long telegramUserId);
}
