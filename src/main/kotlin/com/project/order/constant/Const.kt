package com.project.order.constant

class Const {
    object StateResponse {
        public final const val SUCCESS = "success"
        public final const val FAIL = "fail"
    }

    enum class Role {
        CUSTOMER,
        ADMIN,
        OWN
    }
}