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
        self.list = []

        for x in self.score_key:
            if self.score & self.score_key[x]:
                self.list.append(x)

    def is_allergic_to(self, food):
        if self.score & self.score_key[food]:
            return True
        return False
