package biz.function;

public class PlusOperator  implements Operator{
    @Override
    public int getResult(int v1, int v2) {
        return v1+v2;
    }
}