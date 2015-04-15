class Allergies:
    def __init__(self, bitfield):
        self.list = []

        for a in ['eggs', 'peanuts', 'shellfish', 'strawberries',
                  'tomatoes', 'chocolate', 'pollen', 'cats']:
            if bitfield % 2:
                self.list.append(a)
            bitfield /= 2

    def is_allergic_to(self, name):
        return name in self.list
