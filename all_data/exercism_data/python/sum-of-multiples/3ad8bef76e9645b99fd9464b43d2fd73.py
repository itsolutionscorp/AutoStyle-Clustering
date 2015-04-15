class SumOfMultiples(object):
    
    def __init__(self, *args):
        self.multiples_of = []
        if args:
            for arg in args:
                try:
                    self.multiples_of.append(int(arg))
                except ValueError:
                    print "Must initialize with integers! Defaulted to 3 and 5."
                    self.multiples_of = [3, 5]
                    break
        else:
            self.multiples_of = [3, 5]
    
    def is_mult(self, num):
        return any(i
                    for i in self.multiples_of
                    if num % i == 0)
                
    def to(self, num):
        return sum(i 
                    for i in range(num) 
                    if self.is_mult(i))
