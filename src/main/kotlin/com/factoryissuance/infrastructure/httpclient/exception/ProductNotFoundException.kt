package com.factoryissuance.infrastructure.httpclient.exception

import com.factoryissuance.domain.exception.BusinessException

class ProductNotFoundException(message: String) : BusinessException(message)
