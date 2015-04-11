class SumOfMultiples:

    # Initialize the object given the list of numbers provided
    # If none was given, set the default to 3 and 5
    def __init__(self, *numbers):
        if len(numbers) > 0:
            self.numbers = numbers
        else:
            self.numbers = (3,5)

    # Return the sum of all the multiples of the list of numbers that are less than n
    def to(self,n):

        # Use a set to avoid duplicates
        multiples = set([0])

        # Go through the list of numbers
        for number in self.numbers:
            i = 1

            # Get all the multiples of this number that are less than n
            while number * i < n:
                multiples.add(number * i)
                i += 1

        # Return the sum of all the numbers that we received
        return reduce(lambda x,y: x+y, multiples)
