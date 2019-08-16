package com.kasiyanov.controller.massage;

public enum MenuMassage implements Content {

    MENU_MASSAGE {
        @Override
        public String getContent() {
            return "Введите 1 - если хоте сохранить данные нового пользователя\n" +
                    "Введите 2 - если хоте посмотреть данные зарегистрированного пользователя\n" +
                    "Введите 3 - если хоте отредактировать данные зарегистрированного пользователя\n" +
                    "Введите 4 - если хоте удалить зарегистрированного пользователя\n" +
                    "Введите q - если хоте выйти";
        }
    },
    USER_SAVED_SUCCESSFUL {
        @Override
        public String getContent() {
            return "Пользователь успешно сохранён";
        }
    },
    USER_UPDATED_SUCCESSFUL {
        @Override
        public String getContent() {
            return "Пользователь успешно изменён";
        }
    },
    USER_DELETED_SUCCESSFUL {
        @Override
        public String getContent() {
            return "Пользователь успешно удалён";
        }
    },
    USER_NOT_FOUND_BY_EMAIL {
        @Override
        public String getContent() {
            return "Пользователь с введённым email не найден";
        }
    },
    ENTERED_INCORRECT_DATA {
        @Override
        public String getContent() {
            return "Введены не корректные данные - попробуйте ещё раз";
        }
    },
    WORK_FINISHED {
        @Override
        public String getContent() {
            return "Работа приложения завершена";
        }
    }
}
