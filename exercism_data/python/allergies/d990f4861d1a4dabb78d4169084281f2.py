class Allergies():
    score_key = {
        'eggs': 1,
        'peanuts': 2,
        'shellfish': 4,
        'strawberries': 8,
        'tomatoes': 16,
        'chocolate': 32,
        'pollen': 64,
        'cats': 128
    }

    def __init__(self, score):
        self.score = score

        # populate list of all allergies
        self.list = [allergy for allergy, value in sorted(self.score_key.items(), key=lambda x: x[1])
                     if self.score & self.score_key[allergy]]

    def is_allergic_to(self, food):
        # use bit operative to parse our base2 allergy dict
        return self.score & self.score_key[food]
