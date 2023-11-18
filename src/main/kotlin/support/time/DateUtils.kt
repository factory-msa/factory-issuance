package support.time

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtils {

    companion object {
        fun toLocalDateTime(date: String): LocalDateTime {
            val pattern = "yyyyMMddHHmmss"
            val formatter = DateTimeFormatter.ofPattern(pattern)

            return LocalDateTime.parse(date, formatter)
        }
    }
}
