class SumOfMultiples:
    def __init__(self, *numbers):
        if len(numbers) > 0:
            self.numbers = numbers
        else:
            self.numbers = (3,5)

    def to(self,n):
        multiples = set([0])
        for number in self.numbers:
            i = 1
            while number * i < n:
                multiples.add(number * i)
                i += 1
        return reduce(lambda x,y: x+y, multiples)
