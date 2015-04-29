class SumOfMultiples(object):
    def __init__(self, number1 = 3, number2 = 5, number3 = 0):
        self.number1 = number1
        self.number2 = number2
        self.number3 = number3
        
    def to(self, upperlimit):
        total = 0
        for num in range(upperlimit):
            if self.number3 == 0:
                if num % self.number1 == 0 or num % self.number2 == 0:
                    total += num
            else:        
                if num % self.number1 == 0 or num % self.number2 == 0 or num % self.number3 == 0:
                    total += num
        return total
    


        
