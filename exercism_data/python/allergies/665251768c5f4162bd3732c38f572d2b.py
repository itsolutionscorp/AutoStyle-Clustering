class Allergies(object):

    ALLERGENS = ('eggs peanuts shellfish strawberries tomatoes '
                 'chocolate pollen cats'.split())
 
    VALUE = dict([(w, 2**i) for i, w in enumerate(ALLERGENS)])

    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, item):
        return self.score & self.VALUE[item]

    @property
    def list(self):
        return filter(self.is_allergic_to, self.ALLERGENS)
