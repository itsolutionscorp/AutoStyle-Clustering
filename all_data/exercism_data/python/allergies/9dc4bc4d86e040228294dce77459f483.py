from collections import OrderedDict


class Allergies:
    items = {
        1: 'eggs',
        2: 'peanuts',
        4: 'shellfish',
        8: 'strawberries',
        16: 'tomatoes',
        32: 'chocolate',
        64: 'pollen',
        128: 'cats'}
    items = OrderedDict(sorted(items.items(), key=lambda t: t[0]))

    def __init__(self, score):
        self.list = []
        for k, v in self.items.iteritems():
            if k | score == score:
                self.list.append(v)

    def is_allergic_to(self, item):
        return item in self.list
