items = [
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats'
]

class Allergies(object):
    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, item):
        return item in self.list

    @property
    def list(self):
        return [
            item for index, item in enumerate(items)
            if 2 ** index & self.score
        ]
