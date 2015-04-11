class Phone(object):

    def __init__(self, number):
        self._number = number

    @property
    def number(self):
        number = self._number
        if len(number) == 11:
            if number[0] == '1':
                number = number[1:]
            else:
                number = '0' * 10
        if len(number) == 9:
            number = '0' * 10
        return ''.join([character for character in number if character.isdigit()])

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        number = self.number
        return '(%s) %s-%s' % (number[:3], number[3:6], number[6:])
