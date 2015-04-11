class Allergies:
    THINGS = [
            'eggs',
            'peanuts',
            'shellfish',
            'strawberries',
            'tomatoes',
            'chocolate',
            'pollen',
            'cats'
            ]
    VALUES = {
            'eggs': 1,
            'peanuts': 2,
            'shellfish': 4,
            'strawberries': 8,
            'tomatoes': 16,
            'chocolate': 32,
            'pollen': 64,
            'cats': 128
            }
    def __init__(self, code): 
        self.code = code
        self.list = [x for x in Allergies.THINGS if self.is_allergic_to(x)]

    def is_allergic_to(self, thing):
        return (self.code & Allergies.VALUES[thing]) != 0
