package chess.utils;

public class Pair <T, H>{
    private T val1;
    private H val2;
    public Pair(T val1, H val2){
        this.val1 = val1;
        this.val2 = val2;
    }

    public T get_val1() {
        return val1;
    }

    public void set_val1(T val1) {
        this.val1 = val1;
    }

    public H get_val2() {
        return val2;
    }

    public void set_val2(H val2) {
        this.val2 = val2;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Pair)){
            return false;
        }
        Pair<T, H> comp = (Pair<T, H>) o;
        return comp.get_val1() == val1 && comp.get_val2() == val2;
    }
}
