class Luhn:


    def __init__(self, input):
        self.n = input

    # Returns a list of digits, with every second digit (from right to left) having its value doubled (-9 if >9)
    def addends(self):
        should_double = False
        result = []
        numbers = self.n

        # Iterate for as long as there are digits to process
        while numbers > 0:
            # Extract rightmost digit
            digit = numbers % 10

            # Double the digit's value
            if should_double:
                digit <<= 1

                # If the digit is greater than 9, subtract 9 from it
                if digit > 9:
                    digit -= 9

            # Add the digit to the result
            result.insert(0, digit)

            # Only double every 2 digits
            should_double = not should_double

            # Remove current digit from the number
            numbers /= 10

        return result

    # Returns the check digit
    def checksum(self):
        # Return the rightmost digit of the previously calculated sum of numbers
        return sum(self.addends()) % 10

    # Checks if the number is a valid Luhn number
    def is_valid(self):
        # Check if the check digit is equal to 0
        return self.checksum() == 0

    # Create a valid Luhn number given an input
    @staticmethod
    def create(n):
        luhn = Luhn(n)

        # Check if the given number is already valid
        if not luhn.is_valid():

            # Multiply it by 10 (i.e. add a 0 to the right)
            luhn.n = n * 10

            # If it's valid now, return the new number
            if luhn.is_valid():
                return luhn.n

            # Otherwise, calculate the check digit as: 10 - check_digit and add it to the result
            else:
                return luhn.n + 10 - luhn.checksum()

        # The original number was already valid
        return n
