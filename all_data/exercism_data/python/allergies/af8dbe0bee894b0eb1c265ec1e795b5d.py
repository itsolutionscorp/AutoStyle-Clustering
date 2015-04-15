class Allergies(object):
    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, allergen):
        if self.score % 2 != 0:
            self.score -= 1
            if allergen == 'eggs':
                return True
