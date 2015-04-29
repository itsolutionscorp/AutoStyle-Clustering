def _extract_digits(text):
    return ''.join(character for character in text if character.isdigit())

class Phone(object):
    INVALID_NUMBER = '0' * 10

    def __init__(self, text):
        self.number = _extract_digits(text)

        self._trim_country_code()
        if not self._looks_valid():
            self._mark_as_invalid()

    def _trim_country_code(self):
        if len(self.number) == 11 and self.number.startswith('1'):
            self.number = self.number[1:]

    def _looks_valid(self):
        return len(self.number) == 10

    def _mark_as_invalid(self):
        self.number = Phone.INVALID_NUMBER

    def area_code(self):
        return self.number[:3]

    def prefix(self):
        return self.number[3:6]

    def line_number(self):
        return self.number[6:]

    def pretty(self):
        return '({}) {}-{}'.format(
            self.area_code(), self.prefix(), self.line_number())
