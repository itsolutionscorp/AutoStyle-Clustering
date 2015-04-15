class Phone:
    def __init__(self, number):
        self._number = number


    @property
    def number(self):
        number = [n for n in self._number if n.isnumeric()]

        if len(number) > 10 and number[0] == '1':
            number.pop(0)

        if len(number) != 10:
            number = ['0' * 10]

        return ''.join(number)

    
    def area_code(self):
        return self.number[:3]


    def  pretty(self):
        return '({}) {}-{}'.format(self.area_code(),
                                   self.number[3:6],
                                   self.number[6:])
