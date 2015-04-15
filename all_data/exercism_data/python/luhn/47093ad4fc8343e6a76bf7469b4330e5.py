class Luhn:

    def __init__(self, value):
        self.value = value

    @staticmethod
    def create(value):
        if not Luhn(value).is_valid():
            diff = (10 - Luhn(value * 10).checksum()) % 10
            return (value * 10) + diff
        else:
            return value

    def is_valid(self):
        return self.checksum() == 0

    def checksum(self):
        return sum(self.addends()) % 10

    def addends(self):
        value_as_list = [int(x) for x in str(self.value)]
        value_as_list.reverse()

        for index, val in enumerate(value_as_list):
            if index % 2 == 1:
                val *= 2
                if val > 9:
                    val -= 9
            value_as_list[index] = val

        value_as_list.reverse()
        return value_as_list
