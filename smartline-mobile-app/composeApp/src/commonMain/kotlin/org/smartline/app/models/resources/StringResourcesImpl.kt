package org.smartline.app.models.resources

class EnStringResources : StringResources {
    override val welcomeMessage
        get() = "Welcome to Smartline!"
    override val welcomeButtonText: String
        get() = "Proceed"
    override val emailText: String
        get() = "Email"
    override val loginText: String
        get() = "Login"
    override val englishText: String
        get() = "English"
    override val russianText: String
        get() = "Русский"
    override val kyrgyzText: String
        get() = "Кыргыздар"
    override val numberText: String
        get() = "Number"
    override val enterEmailText: String
        get() = "Enter your email address"
    override val enterNumberText: String
        get() = "Enter your tel. number"
}

class RuStringResources : StringResources {
    override val welcomeMessage
        get() = "Добро пожаловать в Smartline!"
    override val welcomeButtonText: String
        get() = "Вперёд"
    override val emailText: String
        get() = "Адрес эл. почты"
    override val loginText: String
        get() = "Войти"
    override val englishText: String
        get() = "English"
    override val russianText: String
        get() = "Русский"
    override val kyrgyzText: String
        get() = "Кыргыздар"
    override val numberText: String
        get() = "Номер"
    override val enterEmailText: String
        get() = "Введите ваш адрес эл. почты"
    override val enterNumberText: String
        get() = "Ведите ваш номер телефона"
}

class KgStringResources : StringResources {
    override val welcomeMessage
        get() = "Smartline кош келиңиздер!"
    override val welcomeButtonText: String
        get() = "Улантуу"
    override val emailText: String
        get() = "Дарек эл. почта"
    override val loginText: String
        get() = "Кирүү"
    override val englishText: String
        get() = "English"
    override val russianText: String
        get() = "Русский"
    override val kyrgyzText: String
        get() = "Кыргыздар"
    override val numberText: String
        get() = "Бөлмө"
    override val enterEmailText: String
        get() = "Сиздин дарек эл. почта"
    override val enterNumberText: String
        get() = "Телефон номериңизди киргизиңиз"
}