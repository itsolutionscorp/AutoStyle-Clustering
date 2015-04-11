class Luhn(object):

    def __init__(self, number):
        self.number = number

    def addends(self):
        number_string = str(self.number)
        number_list = [int(digit) for digit in number_string]

        # Double every second number, starting from the end
        for x in range(len(number_list) // 2):
            number_list[-2 * (x + 1)] *= 2

        for x in range(len(number_list)):
            if number_list[x] >= 10:
                number_list[x] -= 9

        # Return reverse of parsed list
        return number_list

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return (self.checksum() == 0)

    def create(self):
        number_string = str(self.number)
        for i in range(10):
            new_number = int(number_string + str(i))
            if Luhn(new_number).is_valid():
                return new_number
