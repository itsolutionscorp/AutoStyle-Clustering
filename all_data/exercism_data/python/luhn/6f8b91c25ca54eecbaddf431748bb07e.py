def to_int_arr(number):
    return [int(num) for num in str(number)]


class Luhn(object):

    def __init__(self, number):
        self.number = number

    @staticmethod
    def create(number):
        number *= 10
        checksum = Luhn.calc_checksum(number)
        if checksum == 0:
            return number
        return number + (10 - checksum)

    @staticmethod
    def calc_checksum(number):
        as_int_arr = to_int_arr(number)
        checksum = 0
        checksum += sum(as_int_arr[-1::-2])
        for num in as_int_arr[-2::-2]:
            checksum += sum(to_int_arr(num * 2))
        return checksum % 10

    def checksum(self):
        return Luhn.calc_checksum(self.number)

    def is_valid(self):
        return Luhn.calc_checksum(self.number) == 0

    def addends(self):
        as_int_arr = to_int_arr(self.number)
        for i in range(len(as_int_arr))[-2::-2]:
            as_int_arr[i] = sum(to_int_arr(as_int_arr[i] * 2))
        return as_int_arr
