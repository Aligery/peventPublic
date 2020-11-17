--test data for inset on test database with events
insert into events(event_type_id,
                   ext_ident,
                   planned_at,
                   event_name,
                   description,
                   url,
                   is_deleted,
                   updated_at)
values (1,
        'SOME_ID_FROM_EXTENAL_SYSTEM1',
        NOW(),
        'Бар 100 Рентген',
        'Легендарный бар 100 рентген из Сталкера',
        'https://example.com',
        false,
        now()
        ),
       (1,
        'SOME_ID_FROM_EXTENAL_SYSTEM2',
        NOW(),
        'Бар 200 Рентген',
        'Как бар 100 рентген, но в 2 раза больше',
        'https://example.com',
        false,
        now()
        ),
       --
       (2,
        'SOME_ID_FROM_EXTENAL_SYSTEM3',
        NOW(),
        'Страйкбол на Камышовской',
        'Отличный возможность захуярить босса, и тебе за это ничего не будет',
        'https://example.com',
        false,
        now()
        ),
       (2,
        'SOME_ID_FROM_EXTENAL_SYSTEM4',
        NOW(),
        'Лазертаг на Тульской',
        'Расстреляй коллег, а потом пойдите бухать все вместе',
        'https://example.com',
        false,
        now()

       ),
       --
       (3,
        'SOME_ID_FROM_EXTENAL_SYSTEM5',
        NOW(),
        'Конференция JOKER',
        'Возможность побухать с коллегами на Java конференции и узнать что-то новое',
        'https://example.com',
        false,
        now()
        ),
       (3,
        'SOME_ID_FROM_EXTENAL_SYSTEM6',
        NOW(),
        'Конференция JSHoly',
        'Доклады и рассказы JavaScript разработчик, а также бухлопарк постфактум',
        'https://example.com',
        false,
        now()
        ),
--
       (4,
        null,
        NOW(),
        'Крокодил',
        'Сыграй с коллегами в крокодила, не ну а хули',
        'https://example.com',
        false,
        now()
       ),
       (4,
        null,
        NOW(),
        'Мафия',
        'Узнай кто не умеет врать и используй это в своих целях',
        'https://example.com',
        false,
        now()
       ),
      --
       (5,
        'SOME_ID_FROM_EXTENAL_SYSTEM7',
        NOW(),
        'Квест взлом жопы',
        'Взломай жопу соседу',
        'https://example.com',
        false,
        now()
        ),
       (5,
        'SOME_ID_FROM_EXTENAL_SYSTEM8',
        NOW(),
        'Квест бункер Сталина',
        'Спасись иначе тебя съест лично Сталин',
        'https://example.com',
        false,
        now()
        )



