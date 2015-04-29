class Allergies(object):
    _score_components = ('eggs', 'peanuts', 'shellfish', 'strawberries',
        'tomatoes', 'chocolate', 'pollen', 'cats')
    
    def __init__(self, score):
        self.list = [item for bit, item in enumerate(self._score_components) if
            2**bit & score != 0]

    def is_allergic_to(self, item):
        return (item in self.list)
