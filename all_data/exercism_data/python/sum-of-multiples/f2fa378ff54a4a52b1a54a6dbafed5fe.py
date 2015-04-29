class SumOfMultiples:
    def __init__(self, *arg):
        if arg:
            self.multiples = arg
        else:
            self.multiples = [3, 5]

    def div_by_mults(self, number, multiples):
        options = [number for multiple in self.multiples if number % multiple == 0]
        if options: return True

    def to(self, value):
        return sum(i for i in range(0, value) if self.div_by_mults(i, self.multiples))
