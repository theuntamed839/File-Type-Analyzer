import forkjoin.CustomRecursionTask;
import forkjoin.CustomRecursiveAction;
import forkjoin.util.PoolUtil;
import jsonParser.HelperParser;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
//        CustomRecursionTask myRecursiveAction =
//                new CustomRecursionTask("/media/g2n/LENOVO/Downloads");
//        PoolUtil.forkJoinPool.commonPool().invoke(myRecursiveAction)
//                .parallelStream()
//                .forEach(System.out::println);
        CustomRecursiveAction action = new CustomRecursiveAction("/media/g2n/NewData/IP_IMAGES");
        PoolUtil.forkJoinPool.commonPool().invoke(action);
        System.out.println(action.isDone());
    }
}
