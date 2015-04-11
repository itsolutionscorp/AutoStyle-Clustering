class SumOfMultiples(object):
    def __init__(self, *args):
        if args:
            self.numbers = args
        else:
            self.numbers = 3, 5
        
    def to(self, upperlimit):
        total = 0
        for num in range(upperlimit):
            for number in self.numbers:
                if num % number == 0:
                    total += num
                    break
        return total
    


        
