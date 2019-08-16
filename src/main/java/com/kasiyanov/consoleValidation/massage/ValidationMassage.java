package com.kasiyanov.consoleValidation.massage;

import com.kasiyanov.controller.massage.Content;

public enum ValidationMassage implements Content {

    INCORRECT_EMAIL_MASSAGE {
        @Override
        public String getContent() {
            return "Введён не корректный email - попробуйте ещё раз";
        }
    },
    LIST_CONTAINS_INCORRECT_PHONE {
        @Override
        public String getContent() {
            return "Один или более из номеров телефонов введён не корректно - " +
                    "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую";
        }
    },
    PHONES_LESS_THEN_MIN_LIST_SIZE {
        @Override
        public String getContent() {
            return "Не введено ни одного номера телефонов, " +
                    "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую";
        }
    },
    PHONES_MORE_THEN_MAX_LIST_SIZE {
        @Override
        public String getContent() {
            return "Введено более трёх номеров телефонов, " +
                    "введите от одного до трёх номеров телефонов пользователя в виде 375хх ххххххх через запятую";
        }
    },
    ROLES_LESS_THEN_MIN_LIST_SIZE {
        @Override
        public String getContent() {
            return "Не введено ни одной роли, введите от одной до трёх ролей пользователя через запятую";
        }
    },
    ROLES_MORE_THEN_MAX_LIST_SIZE {
        @Override
        public String getContent() {
            return "Введено более трёх ролей, введите от одной до трёх ролей пользователя через запятую";
        }
    }
}
