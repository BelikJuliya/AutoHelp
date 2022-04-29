package android.example.autohelp.presentation.base

enum class PostModelPayload{
    EVENT, EVENT_LIST, IS_SELECTED, ICON, PHONE_NUMBER, CAR, REGION, LAST_LETTERS, NUMBER, FIRST_LETTER, TITLE, DESCRIPTION, VIDEO_LIST
}

enum class EventType(val type: Int) {
    ROAD_ACCIDENT(1), EVACUATION(2), PARKING(3);
}
enum class VehicleType(val type: Int) {
    AUTO(1),  MOTO(2);
}