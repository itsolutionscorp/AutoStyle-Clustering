class Binary:
    """ Binary converts a binary number to a decimal """

    def __init__(self, binary):
        self.binary = binary

    def __trunc__(self):
        total = 0
        if self.binary.isdigit():
            for index, i in enumerate(reversed(self.binary)):
                total = total + int(i) * 2**index
        return total
