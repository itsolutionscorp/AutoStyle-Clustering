
class SumOfMultiples:
    def __init__(self, *multiples):
        if not multiples:
             multiples = (3,5)
        self.tuple = multiples

    def to(self, limit):
        self.sum = 0
        for i in range(limit):
            self.multipleOf(i)
        return self.sum

    def multipleOf(self, number):
        for multiple in self.tuple:
            if number % multiple == 0:
                self.sum+= number
                break #handles collisions
