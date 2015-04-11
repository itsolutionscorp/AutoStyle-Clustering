class Luhn:

    def __init__(self, num):
        self.number = num

    @staticmethod
    def create(num):
        #appends check digit to regular number
        my_luhn = Luhn(num*10)
        luhn_num = [int(dig) for dig in str(num)]
        if my_luhn.checksum() == 0:
            luhn_num.append((my_luhn.checksum()))
        else:
            luhn_num.append((10-my_luhn.checksum()))
        return int(''.join(map(str, luhn_num)))

    def addends(self):
        #doubles and %9s
        num_arr = [int(dig) for dig in str(self.number)]
        num_arr.reverse()
        for digit in range(0, len(num_arr)):
            if digit % 2 == 1:
                num_arr[digit] *= 2
                if num_arr[digit] > 9:
                    num_arr[digit] -= 9
        num_arr.reverse()
        return num_arr

    def checksum(self):
        #creates check digit
        return sum(self.addends()) % 10

    def is_valid(self):
        #checks a created num+check_digit to see if valid
        my_num = self.number

        if self.create(self.number / 10) == my_num:
            self.number = my_num
            return True
        else:
            self.number = my_num
            return False
