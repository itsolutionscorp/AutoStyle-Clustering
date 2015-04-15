class Luhn():
    def __init__(self, number):
        self.number = [int(i) for i in str(number)]

    def addends(self):
        result = self.number
        position = len(result) % 2
        while position<len(result):
            current = result[position]
            if current*2 > 9:
                result[position] = result[position]*2-9
            else:
                result[position] = result[position]*2
            position += 2
        return result

    def checksum(self):
        return sum(self.addends()) % 10

    def is_valid(self):
        return self.checksum() == 0

    @staticmethod
    def create(number):
        check = Luhn(number*10).checksum()
        check = 10-check if check!=0 else 0
        return number*10+check
