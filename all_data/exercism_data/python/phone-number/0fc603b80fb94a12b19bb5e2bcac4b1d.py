class Phone(object):
    def __init__(self, nr):
        self.number = self._validate(nr)

    def _validate(self, nr):
        clean_nr = ''.join(c for c in nr if c.isdigit())
        if len(clean_nr) == 10:
            return clean_nr
        elif len(clean_nr) == 11 and clean_nr[0] == '1':
            return clean_nr[1:]
        elif len(clean_nr) < 10 or len(clean_nr) > 10:
            return '0000000000'

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({}) {}-{}".format(self.number[:3], self.number[3:6],
                                   self.number[6:10])
