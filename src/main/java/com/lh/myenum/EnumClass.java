package com.lh.myenum;

/**
 * @author 梁昊
 * @date 2018/10/22
 * @function
 * @editLog
 */
public class EnumClass {
    public enum CheckIdentityEnum {
        IS_BS("-bkf-"),//BS
        IS_CS("-ckf-"),//CS
        IS_ANDROID("-akf-"),//Android
        IS_IOS("-ikf-"),//IOS
        IS_WEIXIN_PUBLIC("-wkf-"),//微信公众号
        IS_WEIXIN_SMALLPROGRAME("-skf-"),//微信小程序
        IS_LOCALREMOTE("-lkf-"),//本地跳转
        IS_NO("-nkf-"),//只验证Origin
        IS_OTHER("-okf-");//不验证任何东西
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
