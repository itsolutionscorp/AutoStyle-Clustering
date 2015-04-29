class SumOfMultiples:
    def __init__(self, a = 3, b = 5, c = 0):
        self.a = a
        self.b = b
        self.c = c

    def to(self, limit):
        result = []
        for num in [self.a, self.b, self.c]:
            if num:
                for i in range(num, limit, num):
                    result.append(i)
        result = sum(set(result))
        return result
