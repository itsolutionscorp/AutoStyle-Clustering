ALLERGIES = [ 'eggs',
              'peanuts',
              'shellfish',
              'strawberries',
              'tomatoes',
              'chocolate',
              'pollen',
              'cats']

class Allergies(object):

    def __init__(self, score):
        self.score = score
        scores = [1] + map(lambda a:2**a, range(1, len(ALLERGIES)))
        self.list = [a for k, a in zip(map(lambda s:bool(s&score), scores), ALLERGIES) if k]
        

    def is_allergic_to(self, allergy):
        return allergy in self.list
