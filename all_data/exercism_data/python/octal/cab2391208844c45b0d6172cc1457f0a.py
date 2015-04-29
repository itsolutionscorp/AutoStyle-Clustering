class Octal(object):
    def __init__(self, octal):
        for char in octal:
            if char not in "01234567":
                raise ValueError("Invalid octal digit: %s" %char)

        self.octal = octal[::-1]    # Reverse to get place values correct

    def to_decimal(self):
        result = 0

        for place in range(len(self.octal)):
            result += int(self.octal[place]) * (8 ** place)
            
        return result
