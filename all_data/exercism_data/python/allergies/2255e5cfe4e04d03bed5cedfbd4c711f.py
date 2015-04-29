lists_of_allergens = {
    '0': [],
    '1': ['eggs'],
    '2': ['peanuts'],
    '3': ['eggs', 'peanuts'],
    '4': ['shellfish'],
    '5': ['eggs', 'shellfish'],
    '6': ['peanuts', 'shellfish'],
    '7': ['eggs', 'peanuts', 'shellfish'],
    '8': ['strawberries'],
    '9': ['eggs', 'strawberries'],
    '16': ['tomatoes'],
    '32': ['chocolate'],
    '64': ['pollen'],
    '128': ['cats'],
    '255': [
        'eggs', 'peanuts', 'shellfish', 'strawberries',
        'tomatoes', 'chocolate', 'pollen', 'cats'
    ]
}

class Allergies:
    def __init__(self, allergyNum):
        self.allergyNum = allergyNum
        self.list = self.getAllergies(allergyNum)

    def getAllergies(self, allergyNum):
        if not str(allergyNum) in lists_of_allergens:
            if allergyNum > 256:
                allergyNum -= 256
                return lists_of_allergens[str(allergyNum)]
        return lists_of_allergens[str(allergyNum)]

    def is_allergic_to(self, allergen):
        return allergen in self.list
