__author__ = 'tracyrohlin'

class Luhn:
    def __init__(self, number):
        self.number = map(int, str(number))

    def addends(self):
        rev_num = self.number[::-1]
        for n in range(1,len(rev_num), 2):
            if rev_num[n] *2 >= 10:  #if the number is more or equal to 10, then subtract 9
                rev_num[n] = rev_num[n]*2-9
            else:
                rev_num[n] *= 2
                #otherwise, replace the number with new, doubled number"""

        number = rev_num[::-1]
        return number

    def checksum(self):
        number = self.addends()
        new_num = 0
        for n in number:
            new_num += n
        return new_num % 10  #returns the remainder of the added number

    def is_valid(self):
        remainder = self.checksum()
        if remainder == 0:
            return True
        else:
            return False

    @classmethod
    def create(cls, number):
        new_instance = Luhn(number)
        x = 0
        while x < 10:
            new_instance.number.append(x)
            if new_instance.is_valid():
                result = "".join([str(x) for x in new_instance.number])
                return int(result)
            new_instance.number.pop()
            x += 1

print Luhn.create(837263756)
