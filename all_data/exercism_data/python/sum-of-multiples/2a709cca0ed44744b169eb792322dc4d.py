class SumOfMultiples(object):

    def __init__(self, *numbers):
        # Default configuration is finding multiples of 3 or 5.
        # So if no numbers are given, use 3 and 5.
        self.numbers = numbers
        if len(self.numbers)==0: self.numbers=[3,5]

    def multiples_of(self, number, max):
        # Return the multiples of _number_ in _max_ natural numbers.
        return [x for x in range(number, max, number)]

    def to(self, max):
        # A set to hold our multiples.
        multiples_set = set()

        # Loop through the given numbers to get multiples. Add them to the
        # Multiples set, so we don't have duplicate entries.    
        for number in self.numbers:
            for multiple in self.multiples_of(number, max):
                multiples_set.add(multiple)

        # Return the sum of the set.
        return sum(multiples_set)
