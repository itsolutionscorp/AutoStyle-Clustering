public class ModNCounter extends Counter {


int myMax;


public ModNCounter (int n) {

myMax = n;

}

public void increment() {

if (this.value() == myMax) {

this.reset();

} else

super.increment();

}

}