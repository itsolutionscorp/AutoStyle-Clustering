class Phone:
    __valid_length = 10
    __wrong_number = '0' * __valid_length
  
    def __init__(self, text):
        self.number = ''.join(filter(str.isdigit, text))
        if len(self.number) == 11 and self.number[0] == '1':
            self.number = self.number[1:]
        elif len(self.number) != self.__valid_length:
            self.number = self.__wrong_number

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return '({0}) {1}-{2}'.format(self.number[:3], self.number[3:6], self.number[6:])
