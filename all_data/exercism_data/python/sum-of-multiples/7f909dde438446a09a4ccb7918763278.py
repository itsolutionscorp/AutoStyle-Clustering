class SumOfMultiples(object):
    def __init__(self, *multiples):
        if not multiples:
            self.multiples = [3, 5]
        else: self.multiples = multiples

    def to(self, num):
        def mod_check(x):
            return any(
                [ x % multiple == 0 for multiple in self.multiples ]
            )

        return sum(
            x for x in range(num)
            if mod_check(x)
        )
