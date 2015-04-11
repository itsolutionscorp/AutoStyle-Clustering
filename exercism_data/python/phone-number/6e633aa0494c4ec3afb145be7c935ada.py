import string

class Phone:
    def __init__(self, number):
        self.number = number
        self._clean_non_digits()
        self._clean_international_prefix()
        self._null_out_invalid_number()

    def _clean_non_digits(self):
        self.number = "".join(c for c in self.number
                              if c in string.digits)

    def _clean_international_prefix(self):
        if len(self.number) == 11 and self.number[0] == '1':
            self.number = self.number[1:]

    def _null_out_invalid_number(self):
        if len(self.number) != 10:
            self.number = "0" * 10

    def area_code(self):
        return self.number[0:3]
        
    def prefix(self):
        return self.number[3:6]

    def line(self):
        return self.number[6:10]

    def pretty(self):
        return "({}) {}-{}".format(self.area_code(),
                                   self.prefix(),
                                   self.line())

    pass
