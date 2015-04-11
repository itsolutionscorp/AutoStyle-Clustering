class Allergies:
    def __init__(self, bitfield):
        self.list = []
        allergen = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                    'tomatoes', 'chocolate', 'pollen', 'cats']

        if bitfield:
            for i in range(len(allergen)):
                if bitfield % 2:
                    self.list.append(allergen[i])
                bitfield /= 2
                if not bitfield:
                    break

    def is_allergic_to(self, name):
        return name in self.list
