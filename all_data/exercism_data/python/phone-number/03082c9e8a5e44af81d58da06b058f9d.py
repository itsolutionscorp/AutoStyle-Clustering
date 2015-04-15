class Phone(object):
    def __init__(self, phone_number):
        self.number = self.__clean_and_validate(phone_number)

    @staticmethod
    def __clean_and_validate(phone_number):
        digits = [char for char in phone_number if char.isdigit()]
        if len(digits) == 10:
            return ''.join(digits)
        if (len(digits) == 11) and (digits[0] == '1'):
            return ''.join(digits[1:])
        return '0' * 10     # bad number

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        area_code = self.area_code()
        part2, part3 = self.number[3:6], self.number[6:]
        return "({}) {}-{}".format(area_code, part2, part3)
