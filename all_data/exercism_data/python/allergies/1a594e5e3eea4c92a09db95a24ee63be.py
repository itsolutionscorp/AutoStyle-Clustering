class Allergies:
    ALLERGEN_TYPES = {'eggs': 1,
                      'peanuts': 2,
                      'shellfish': 4,
                      'strawberries': 8,
                      'tomatoes': 16,
                      'chocolate': 32,
                      'pollen': 64,
                      'cats': 128
                      }

    def __init__(self, score):
        """
        Note to self -
        sorted(self.ALLERGEN_TYPES.items(), key=lambda x: x[1]) will return a
        list of the dictionary values sorted by score value. So if values
        change in future it is possible to easily amend
        """
        self.score = score
        # For ease of use let's store the score as a binary number, but reverse
        # it so that the lowest integer is on the left. This also allows us
        # to ignore all values >=256
        self.score_ref = '{:08b}'.format(score)[-8:][::-1]

        self.list = []
        tmp_dct_sort = sorted(self.ALLERGEN_TYPES.items(), key=lambda x: x[1])

        for pos, val in enumerate(self.score_ref):
            if val == '1':
                self.list.append(tmp_dct_sort[pos][0])

    def is_allergic_to(self, allergen):
        return allergen in self.list
