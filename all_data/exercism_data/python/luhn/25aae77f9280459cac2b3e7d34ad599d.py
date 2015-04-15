class Luhn(object):

    def __init__(self, number):
        self.number = number

    def addends(self, offset=1):
        number_string = str(self.number)
        number_list = [int(digit) for digit in number_string]
        reversed_list = number_list[::-1]

        # Double every second number
        reversed_list[offset::2] = [2 * x for x in reversed_list[offset::2]]

        # Subtract 9 from numbers >= 10
        parsed_reversed_list = []
        for x in reversed_list:
            if x >= 10:
                parsed_reversed_list.append(x - 9)
            else:
                parsed_reversed_list.append(x)

        # Return reverse of parsed list
        return parsed_reversed_list[::-1]

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
