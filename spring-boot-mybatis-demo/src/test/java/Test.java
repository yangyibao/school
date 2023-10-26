import com.study.model.SerCompanyVO;

public class Test {
    public static void main(String[] args) {
        SerCompanyVO serCompanyVO = new SerCompanyVO();
        serCompanyVO.setCompanyId(515174);
        while(true){
            try {
                System.out.println( serCompanyVO.getCompanyId());
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
