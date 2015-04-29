class SumOfMultiples:
    def __init__(self, *args):
        if args:
            self.args = args
        else:
            self.args = (3, 5)

    def to(self, num):
        return sum(set(i for multiple in self.args for i in range(num) if not i % multiple))
