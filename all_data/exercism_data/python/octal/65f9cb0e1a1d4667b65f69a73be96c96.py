from string import octdigits

class Octal:
    def __init__(self, s):
        self.digits = []
        for c in s:
            if c not in set(octdigits):
                raise ValueError("Invalid octal digit: {}".format(c))
            self.digits.append(int(c))
    
    def to_decimal(self):
        return reduce(lambda x,y: x*8+y, self.digits)
