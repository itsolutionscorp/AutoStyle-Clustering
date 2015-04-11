class SumOfMultiples:
    def __init__(self, *arg):
        self.numbers = [3, 5]
        if len(arg): 
            self.numbers = arg

    def to(self, limit):
        match = []

        for n in self.numbers:
            m = n
            while m < limit:
                if m not in match:
                    match.append(m)
                m += n

        return sum(match)
