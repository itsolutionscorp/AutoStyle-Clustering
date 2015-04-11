class Binary(object):

    """Binary number"""

    def __init__(self, number):
        """Create new binary number from string."""
        self.number = number

    def __int__(self):
        """Convert to decimal integer."""
        value = 0
        for power, digit in enumerate(reversed(self.number)):
            if digit == "1":
                value += 2 ** power
        return value
