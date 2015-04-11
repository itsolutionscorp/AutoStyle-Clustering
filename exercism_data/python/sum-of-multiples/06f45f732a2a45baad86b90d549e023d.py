class SumOfMultiples(object):
    def __init__(self, *args):
        self._mults = [3, 5]

        if len(args) > 0:
            self._mults = []
        
        for arg in args:
            self._mults.append(arg)

    def to(self, n):
        sum = 0

        for num in xrange(1, n):
            for mult in self._mults:
                if num % mult == 0:
                    sum += num
                    break

        return sum
