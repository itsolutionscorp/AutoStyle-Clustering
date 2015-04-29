class Luhn(object):
    """Performs a variety of functions involving the Luhn Algorithm"""

    def __init__(self, value):
        """Creates an object that stores a given integer value
        for actions involving the Luhn Algorithm
        """
        self._value = value

    def addends(self):
        """Converts the stored value into a list
        using the Luhn Algorithm
        """
        value_list = [int(i) for i in str(self._value)]
        for i in range(len(value_list)-2,-1,-2):
            new_val = value_list[i]*2
            value_list[i] = new_val if new_val < 10 else new_val-9
        return value_list

    def checksum(self):
        """Returns the checksum of the stored value"""
        return sum(self.addends()) % 10

    def is_valid(self):
        """Checks the stored value to see if it valid
        according to the Luhn formula.
        """
        return self.checksum()==0

    @staticmethod
    def create(value):
        """Given a value, adds a check digit to make the
        value valid per the Luhn formula.
        """
        checksum = 10-Luhn(value*10).checksum()
        return value*10 if checksum == 10 else value*10+checksum
