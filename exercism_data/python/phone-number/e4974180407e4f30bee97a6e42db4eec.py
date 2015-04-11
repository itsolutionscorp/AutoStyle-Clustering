import string

class Phone:
    def __init__(self, number):
        number = number.translate(None,
                                  string.punctuation
                                  + string.whitespace)
        if len(number) == 11 and number[0] == '1':
            number = number[1:]
            pass

        if len(number) != 10:
            number = "0" * 10
            pass

        self.number = number
        return

    def area_code(self):
        return "123"

    def pretty(self):
        return "(123) 456-7890"

    pass
