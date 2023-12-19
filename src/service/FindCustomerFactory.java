package service;
import constant.Constants;

public class FindCustomerFactory {
    public static IFind createIFind(int flag){
        switch (flag){
            case Constants.FIND_NAME : {
                return new FindByName();
            }
            case Constants.FIND_EMAIL : {
                return new FindByEmail();
            }
            case Constants.FIND_PHONE_NUMBER : {
                return new FindByPhoneNumber();
            }
            default:{
                return null;
            }
        }
    }
}
