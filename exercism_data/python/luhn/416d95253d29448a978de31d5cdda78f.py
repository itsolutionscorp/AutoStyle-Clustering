class Luhn(object):

    def __init__(self, number):
        self.number = number

    def addends(self):
        digit_arr = [int(i) for i in str(self.number)]
        for i in range(len(digit_arr)-2,-1,-2):
            digit_arr[i] *= 2
            if digit_arr[i] > 9:
                digit_arr[i] -= 9
        return digit_arr

    def checksum(self):
        sum = 0
        for i in self.addends():
            sum += i
        return sum%10

    def is_valid(self):
        if self.checksum() == 0:
            return True
        else:
            return False

    @staticmethod
    def create(number):
        new_num = Luhn(number * 10)
        if new_num.is_valid():
            return new_num.number
        else:
            return new_num.number + (10 - new_num.checksum())
