class Allergies:

    def __init__(self, score):
        self.list = []
        for i, entry in enumerate(['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes',
        'chocolate', 'pollen', 'cats']):
            if score & (0x1 << i):
                self.list.append(entry)


    def is_allergic_to(self, allergystring):
        return allergystring in self.list

