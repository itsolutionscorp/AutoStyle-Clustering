import collections

# Allergies from most significant bit in encoding to the least significant bit
_allergies = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries',
              'shellfish', 'peanuts', 'eggs']

class Allergies(object):
    def __init__(self, score):
        self.list = self.determine_allergies(score)

        # Store in a dictionary that returns whether patient has an allergy
        self.dict = collections.defaultdict(bool)  # Defaults to False
        self.dict.update(zip(self.list, [True]*len(self.list)))

    def is_allergic_to(self, allergy):
        return self.dict[allergy]

    def determine_allergies(self, score):
        allergies = []  # List of allergies to return

        # Convert the score integer to its 8-bit binary representation
        encoding = bin(score).replace('0b', '')
        if len(encoding) < 8:
            encoding = '0'*(8 - len(encoding)) + encoding  # Pad encoding
        elif len(encoding) > 8:
            encoding = encoding[-8:]  # Truncate encoding

        # Loop over the bits (representing allergies) in the encoding
        for index, bit in enumerate(map(int, encoding[-8:])):
            if bit:  # If the bit is set, the person has the allergy
                allergies.append(_allergies[index])

        # Test script expects allergies in ascending order, with respect to
        # their score
        allergies.reverse()

        return allergies
