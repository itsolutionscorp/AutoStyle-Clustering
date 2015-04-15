class Allergies:
    _allergyList = ['eggs', 'peanuts', 'shellfish', 'strawberries',
                    'tomatoes', 'chocolate', 'pollen', 'cats']
 
    def __init__(self, num):
        self.allergies = list(bin(num)[2:])
        self.allergies.reverse()

    def is_allergic_to(self, allergy):
        for i, v in enumerate(self.allergies):
            if v == '1' and i < len(self._allergyList):
                if allergy == self._allergyList[i]:
                    return True
        return False

    @property
    def list(self):
        return [a for a in self._allergyList if self.is_allergic_to(a)]
