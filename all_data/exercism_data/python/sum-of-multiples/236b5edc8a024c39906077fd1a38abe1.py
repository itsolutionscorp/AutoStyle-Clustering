class SumOfMultiples:
    def __init__(self, *numbers):
        self.sum = 0
        self.takeaway = 1
        if not numbers:
            self.multiples = [3, 5]
        else:
            self.multiples = list(numbers)
    def to(self, number):
        for a in self.multiples:
            a1 = a
            self.takeaway *= a
            while a1 < (number):
                self.sum += a1
                a1 += a
        b = self.takeaway
        while b < (number):
            self.sum -= b
            b += self.takeaway
        return self.sum
            
        
