class SumOfMultiples(object):
    def __init__(self,*args):
        self.multipliers = args if args else [3,5]

    def to(self,limit):
        numbers = [i for i in range(1,limit)]
        result = 0
        for y in self.multipliers:        
            for x in numbers:
                if x % y == 0:
                    result += x
                    numbers.remove(x)
        return result
