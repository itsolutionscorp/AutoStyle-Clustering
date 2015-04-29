class SumOfMultiples:
    def __init__(self, *multiples):
        self.multiples = [3, 5]
        if multiples:
            self.multiples = multiples

    def to(self, max_num):
        result = 0
        for x in range(max_num):
            for mult in self.multiples:
                if x % mult == 0:
                    result += x
                    break
        return result
