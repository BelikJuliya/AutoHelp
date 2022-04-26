package android.example.autohelp.presentation.base

enum class PostModelPayload{
    EVENT, EVENT_LIST, IS_SELECTED, ICON, PHONE_NUMBER, CREATE_DATE, METRICS, SUBSCRIBE, LIST, LIKE, STATE, CHECKED, ENABLED, CHECK, PROGRESS, LOADING, IMAGE, TEXT, ITEMS, ERROR_TEXT, EMPTY, NAME, NICKNAME, PHOTO, DESCRIPTION, SURNAME,
    SUBSCRIBERS, PROMOCODE, DEEPLINK, TYPE, TITLE, AMOUNT, DATE, BALANCE, STATUS, IS_DAILY_COINS_RECEIVED, PUSH_SETTINGS, IS_REAL, IS_CONFIRMED, IS_ACCEPTED, SUM, FIRSTNAME, LASTNAME,
    INN, ACCOUNT, BIK, IS_VALID, IS_CHECKED, COIN_AGREEMENT, UNREAD_COUNT, LAST_MESSAGE, IS_EXPANDED, VIDEO, COVER, RATING, PRICE, PARAMS, CATEGORIES, NEWS, HINT
}

enum class EventType(val type: Int) {
    ROAD_ACCIDENT(1), EVACUATION(2), PARKING(3);
}