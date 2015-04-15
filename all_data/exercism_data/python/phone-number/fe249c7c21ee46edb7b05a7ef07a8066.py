from string import punctuation


class Phone:
    def __init__(self, raw_number):

        self.number = self.convert_number(
            self.cleaned_number(raw_number))

    def cleaned_number(self, raw_number):
        return raw_number.translate(None, punctuation).translate(None, ' ')

    def convert_number(self, number):
        invalid_number = "0000000000"

        if len(number) < 10:
            r_number = invalid_number
        elif len(number) == 11:
            r_number = number[1:] if number.startswith("1") else invalid_number
        else:
            r_number = number
        return r_number

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "(%s) %s-%s" % (self.area_code(),
                               self.number[3:6],
                               self.number[6:])
