class SumOfMultiples(object):

    def __init__(self,multiples_a = 3,multiples_b = 5,multiples_c = None):
        self.multiples_a = multiples_a
        self.multiples_b = multiples_b
        self.multiples_c = multiples_c
        
    def to(self,number):
    
        if self.multiples_c == None:
            return sum([value for value in range(number) if value%self.multiples_a==0 or value%self.multiples_b==0])
            
        return sum([value for value in range(number) if value%self.multiples_a==0 or value%self.multiples_b==0 or value%self.multiples_c==0])
