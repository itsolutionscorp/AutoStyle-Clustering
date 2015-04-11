# Does not pass test 6 (2223370)
#Does not pass test  4 (266333)

class SumOfMultiples:
    def __init__(self, *args):
        multiples = []
        if len(args) == 0:
            self.multiples = [3,5]
        else:
            for i in args:
                multiples.append(i)
            self.multiples = multiples

    def to(self, number):
        total = 0
        for n in range(1, number):
            if n == number:
                continue
            for multiple in self.multiples:
                if n % multiple == 0:
                    total += n
        return total
