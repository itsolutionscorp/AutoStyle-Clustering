class Luhn:
    def __init__(self, number):
        self.num_list = [int(num) for num in str(number)]

    def number(self):
        return int("".join([str(num) for num in self.num_list]))
        
    def checksum(self):
        return sum(num for num in self.addends()) % 10

    def addends(self):
        adds = self.num_list[:]
        for idx in range(len(adds)-2, -1, -2):
            value = adds[idx] * 2
            if value >= 10:
                value -= 9
            adds[idx] = value

        return [num for num in adds]

    def create(number):
        new_luhn = Luhn(number * 10)
        check = new_luhn.checksum()
        new_luhn.num_list[-1] = (10 - check) % 10
        return new_luhn.number()

    def is_valid(self):
        return not self.checksum()
