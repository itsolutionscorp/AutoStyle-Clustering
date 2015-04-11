class Luhn:
    def __init__(self,number):
        self.n = number

    def addends(self):
        new_number = self.n
        num_list = [int(i) for i in str(new_number)]
        l = len(num_list) 

        while l > 1:
            if num_list[l-2] >= 5:
                num_list[l-2] = 2 * num_list[l-2] - 9
            else:
                num_list[l-2] = 2 * num_list[l-2]
            l -= 2
        return num_list

    def checksum(self):
        digits = Luhn(self.n).addends()
        number = 0
        for num in digits:
            number += num
        return number % 10

    def is_valid(self):
        return Luhn(self.n).checksum() == 0

    def create(n):
        new_number = n
        if not Luhn(new_number).is_valid():
            new_number = 10*n
        while not Luhn(new_number).is_valid():
            new_number += 1
        return new_number
