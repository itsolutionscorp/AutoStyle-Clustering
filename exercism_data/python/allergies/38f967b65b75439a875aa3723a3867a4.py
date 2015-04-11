class Allergies(object):
    def __init__(self, score):
        self.score_array = bin(score)[2:].zfill(8)[::-1]
        self.list = []
        allergen_collection = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
        flt = zip(self.score_array, allergen_collection)
        self.list = [x[1] for x in flt if x[0] == '1']

    def is_allergic_to(self, tested):
        """
        * eggs (1)
        * peanuts (2)
        * shellfish (4)
        * strawberries (8)
        * tomatoes (16)
        * chocolate (32)
        * pollen (64)
        * cats (128)
        """

        if tested in self.list:
            return True
        else:
            return False
