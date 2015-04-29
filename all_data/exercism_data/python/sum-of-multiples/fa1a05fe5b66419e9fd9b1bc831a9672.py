class SumOfMultiples():
    def __init__(self, *args):
        self.args = args if args else (3, 5)

    def to(self, limit):
        multiples = []
        for arg in self.args:
            all_nums = range(arg, limit)
            multiples.extend([multiple for multiple in all_nums if multiple % arg == 0])

        return sum(set(multiples))
