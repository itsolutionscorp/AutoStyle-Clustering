class SumOfMultiples():
    
    def __init__(self, *args):
        self.variables = args or (3, 5)

    def to(self, upper_bound):
        total = 0

        for number in range(1, upper_bound):
            is_multiple = bool(list(filter(lambda x: number % x == 0, self.variables)))

            if is_multiple:
                total += number

        return total
