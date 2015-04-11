class SumOfMultiples:

    def __init__(self, *args):
        if not args:
            self.numbers = [3, 5]
        else:
            self.numbers = args
    
    def to(self, n):
        total = 0
        for number in self.numbers:
            count = (n - 1) / number
            total += number * ((count) * (count + 1)) / 2
        lcm = reduce(lambda x,y: x*y, self.numbers)
        count = (n - 1) / lcm
        total -= lcm * ((count) * (count + 1)) / 2
        return total
