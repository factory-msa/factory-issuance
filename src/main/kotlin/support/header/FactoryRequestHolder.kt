package support.header

class FactoryRequestHolder {

    companion object {
        private val threadLocal = ThreadLocal<FactoryHeader>()

        fun get(): FactoryHeader {
            return threadLocal.get() ?: FactoryHeader.empty()
        }

        fun getGlobalTransactionId(): String {
            val factoryHeader = this.threadLocal.get()

            return factoryHeader.getGlobalTransactionId()
        }

        fun set(data: FactoryHeader) {
            this.threadLocal.set(data)
        }

        fun clear() {
            threadLocal.remove()
        }
    }
}

