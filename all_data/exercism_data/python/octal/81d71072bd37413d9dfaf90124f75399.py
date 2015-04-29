class Octal(object):

    def __init__(self, input_oct_str):
        self.__length = len(input_oct_str)
        for each in input_oct_str:
            if each.isalpha() or int(each) >= 8:
                raise ValueError('Invalid octal digit: %s' % each)
        self.oct_list = [int(c) for c in input_oct_str]


    def to_decimal(self):
        self.dec = sum([self.oct_list[i]*8**(self.__length-i-1)
                        for i in range(self.__length)])
        return self.dec
