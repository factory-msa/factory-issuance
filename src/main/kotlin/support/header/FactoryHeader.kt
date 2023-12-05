package support.header

class FactoryHeader(
    private val globalTransactionId: String?
) {

    companion object {
        const val GLOBAL_TRANSACTION_ID_HEADER= "GLBL-TRX-ID"

        fun empty(): FactoryHeader {
            return FactoryHeader("")
        }
    }

    fun getGlobalTransactionId(): String {
        return globalTransactionId ?: ""
    }

}
