from string import punctuation


class Phone:
    def __init__(self, raw_number):

        self.number = self.convert_number(raw_number)

    def cleaned_number(self, raw_number):
        
        return raw_number.translate(None, punctuation + ' ')

    def convert_number(self, number):

        r_number = self.cleaned_number(number)
        invalid_number = "0000000000"

        if len(r_number) < 10:
            r_number = invalid_number
        elif len(r_number) == 11:
            r_number = (r_number[1:] if r_number.startswith("1")
                        else invalid_number)
        return r_number

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "(%s) %s-%s" % (self.area_code(),
                               self.number[3:6],
                               self.number[6:])
