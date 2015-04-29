class Luhn():
    def __init__(self, number):
        self.number = number


    def addends(self, number=None):
        if not number:
            number = self.number
        not_even = lambda x: x % 2 != 0
        list_number = [int(digit) for digit in str(number)]
        luhn_double = lambda x: 2 * x if x < 5 else 2 * x - 9 
        luhn = []
       # luhn = [luhn_double(value) if not_even(index) for index, value in enumerate(reversed(list_number)) else value]
        #return luhn
        for index, value in enumerate(reversed(list_number)):
            if not_even(index):
                luhn.append(luhn_double(value))
            else:
                luhn.append(value)
        return luhn


    def is_valid(self):
        return self.checksum() == 0


    def checksum(self):
        return sum(self.addends()) % 10


    def create(self, number):
        extra = [int(digit) for digit in "0123456789"]
        number = [int(digit) for digit in str(number)]
        return number
        


if __name__ == "__main__":
    a = Luhn(8631)
    print a.number
    print a.addends()
    #print Luhn(8631).addends()
