class Allergies:
    def __init__(self, total):
        self.total = total
        self.list = Allergies.decode_allergies(self.total)

    def __str__(self):
        return 'Tom is allergic to:\n\t' + '\n\t'.join(self.list)

    @staticmethod
    def decode_allergies(total):
        allergies = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        return [allergy for allergy in allergies if bool(total & 2 ** allergies.index(allergy))]

    def is_allergic_to(self, allergy):
        return allergy in self.list
