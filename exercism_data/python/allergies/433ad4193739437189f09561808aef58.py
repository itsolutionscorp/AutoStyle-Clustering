"""List of allergies.
Note that the position is very important.
The score of a given allergen is 2 to the power of that allergen's index in the list.
"""
_ALLERGY_LIST = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats',
]


class Allergies:
    def __init__(self, score):
        self.score = score
        self.list = [f for f in _ALLERGY_LIST if self.is_allergic_to(f)]

    def is_allergic_to(self, food):
        # Given that the allergy list is basically a binary string, a bitwise 
        # AND of the score and 2 to the power of the food's index tells you 
        # if the food is in the score
        if food in _ALLERGY_LIST:
            return (pow(2, _ALLERGY_LIST.index(food)) & self.score) != 0
        return False
