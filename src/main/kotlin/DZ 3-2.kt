const val PAY_CARD_MASTERCARD = "Mastercard"
const val PAY_CARD_MAESTRO = "Maestro"
const val PAY_CARD_VISA = "Visa"
const val PAY_CARD_MIR = "Mir"
const val PAY_CARD_VK = "Vk"

val comissionSumm = 3500
val transferAmount = 700        // Сумма перевода
val amount = transferAmount * 100 // Переводим в копейки
fun commissionPercentage(
    amount: Int,
    commissionAmount: Double,
    transferMethod: String,
    ): Number {
    val result = amount * commissionAmount   //Сумма комиссии
    val resultComission = when (transferMethod) {
        PAY_CARD_MASTERCARD, PAY_CARD_MAESTRO -> {
            if (transferAmount > 75000) result + 2000 else 0
        }
        PAY_CARD_VISA, PAY_CARD_MIR -> {
            if (result > comissionSumm)
                result else comissionSumm
        }    else -> 0
    }
    return resultComission
}

fun main() {
    val transferMethod = "Mir"         // Система перевода
    val commissionAmount: Double = when (transferMethod) {   //Стандартная Скидка системы перевода %
        PAY_CARD_MASTERCARD, PAY_CARD_MAESTRO -> .06
        PAY_CARD_VISA, PAY_CARD_MIR -> .075
        PAY_CARD_VK -> 0.0
        else -> error("Неизвестная система переводов")
    }

    val commission = commissionPercentage(amount, commissionAmount, transferMethod)
    println("Сумма перевода составляет: ${amount / 100} рублей " +
                "сумма комиссии за перевод: $commission копеек")
}