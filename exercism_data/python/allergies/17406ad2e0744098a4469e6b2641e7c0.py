allergy_list = ['eggs',
                'peanuts',
                'shellfish',
                'strawberries',
                'tomatoes',
                'chocolate',
                'pollen',
                'cats']

class Allergies:
    def __init__(self, score):
        self.list = []
        self._populate_list(score)

    def is_allergic_to(self, allergy):
        if allergy in self.list:
            return True
        return False

    def _populate_list(self, score):
        for idx, allergy in enumerate(allergy_list):
            # Gets the binary value of score at the position of idx.
            if score&(1<<idx):
                self.list.append(allergy)
