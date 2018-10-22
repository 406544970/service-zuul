package com.lh.servicezuul.myenum;

/**
 * @author 梁昊
 * @date 2018/10/22
 * @function
 * @editLog
 */
public class OperateClass {
    public enum CheckIdentityEnum {
        /**
         * BS客户端*/
        IS_BS("-bkf-"),//BS
        IS_CS("-ckf-"),//CS
        IS_ANDROID("-akf-"),//Android
        IS_IOS("-ikf-"),//IOS
        IS_WEIXIN_PUBLIC("-wkf-"),//微信公众号
        IS_WEIXIN_SMALLPROGRAME("-skf-"),//微信小程序
        IS_LOCALREMOTE("-lkf-");//本地跳转

        private String text;

        CheckIdentityEnum(String _context) {
            this.text = _context;
        }

        @Override
        public String toString() {
            return this.text;
        }
    }
}
