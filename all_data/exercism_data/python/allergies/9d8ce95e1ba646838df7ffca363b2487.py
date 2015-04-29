class Allergies(object):
    def __init__(self, num):
        self.num = num
    mask = {'eggs': 0b1, 'peanuts': 0b10, 'shellfish': 0b100, 'strawberries': 0b1000, 'tomatoes': 0b10000, 'chocolate': 0b100000, 'pollen': 0b1000000, 'cats': 0b10000000}

    def is_allergic_to(self, allergy):
        if self.num & self.mask[allergy]:
            return True
        else:
            return False
