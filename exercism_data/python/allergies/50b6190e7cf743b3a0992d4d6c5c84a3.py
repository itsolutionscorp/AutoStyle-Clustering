class Allergies:
    ALLERGEN_TO_VALUE = {
        'eggs': 1,
        'peanuts': 1 << 1,
        'shellfish': 1 << 2,
        'strawberries': 1 << 3,
        'tomatoes': 1 << 4,
        'chocolate': 1 << 5,
        'pollen': 1 << 6,
        'cats': 1 << 7,
    }


    VALUE_TO_ALLERGEN = dict((v, k) for (k, v) in ALLERGEN_TO_VALUE.items())


    def __init__(self, score):
        self.score = score

        # need to sort keys because list order
        # is expected to be increasing based on value
        self.list = [Allergies.VALUE_TO_ALLERGEN[val] \
                     for val in sorted(Allergies.VALUE_TO_ALLERGEN.keys()) \
                     if val & self.score]


    def is_allergic_to(self, allergen):
        if allergen not in Allergies.ALLERGEN_TO_VALUE:
            return False
        else:
            return Allergies.ALLERGEN_TO_VALUE[allergen] & self.score
