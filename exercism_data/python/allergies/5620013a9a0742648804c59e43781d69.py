class Allergies:
    def __init__(self, score):
        self.score = score
        self.shifts = ['eggs', 'peanuts', 'shellfish',
                       'strawberries', 'tomatoes',
                       'chocolate', 'pollen', 'cats']
        self.list = [item for item in self.shifts
                     if self.is_allergic_to(item)]

    def is_allergic_to(self, item):
        return bool(int(bin(self.score >> self.shifts.index(item))[-1]))
