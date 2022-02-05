package bo;

import bo.custom.impl.ManageCourseBOImpl;
import bo.custom.impl.ManageStudentBOImpl;

public class BoFactory {
        private static BoFactory boFactory;

        private BoFactory(){

        }

        public static BoFactory getBoFactory(){
            return (boFactory==null) ? boFactory=new BoFactory() : boFactory;
        }

        public enum BoTypes{
            STUDENT,Course
        }

        public SuperBo getBO(BoTypes types){
            switch (types){
                case STUDENT: return new ManageStudentBOImpl();
                case Course: return new ManageCourseBOImpl();
                default: return null;
            }
        }
}
