def Base(base_, name_):
    all_digits = "0123456789" + "".join(chr(i) for i in xrange(ord('a'), ord('z')+1))
    if base_ > len(all_digits):
        raise ValueError("Cannot create a numbering base {}: not enough digits".format(base_))

    class Base(object):
        digits = all_digits[:base_]
        base = base_
        name = name_

        def __init__(self, s):
            self.num = s
            acc = 0
            b = self.base
            for sd in self.num:
                try:
                    d = self.digits.index(sd)
                    acc *= b
                    acc += d
                except ValueError:
                    raise ValueError("Invalid {} digit: {}".format(self.name, sd))
            self.value = acc

        def to_decimal(self):
            return self.value

    return Base

class Octal(Base(8, 'octal')):
    pass
