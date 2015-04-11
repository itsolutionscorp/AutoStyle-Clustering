table = {
            1   : 'eggs',
            2   : 'peanuts',
            4   : 'shellfish',
            8   : 'strawberries',
            16  : 'tomatoes',
            32  : 'chocolate',
            64  : 'pollen',
            128 : 'cats'
}

class Allergies(object):

    def __init__(self, score):
        self.score = self._set_score(score)
        self.list = self.create_list()

    def _set_score(self, score):
        while (score > 256):
            score = self._set_score(score - 256)
        return score

    def create_list(self):
        allergies = []
        cur_score = self.score
        for score in sorted(table, reverse=True):
            if cur_score >= score:
                cur_score = cur_score - score
                allergies.insert(0, table[score])
        return allergies

    def is_allergic_to(self, item):
        return item in self.list
