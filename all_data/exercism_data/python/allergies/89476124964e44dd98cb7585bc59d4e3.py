from collections import OrderedDict


class Allergies(object):
    def __init__(self, score):
        self.score = score % 256
        self.dic = {
            'cats': 128,
            'pollen': 64,
            'chocolate': 32,
            'tomatoes': 16,
            'strawberries': 8,
            'shellfish': 4,
            'peanuts': 2,
            'eggs': 1,
        }
        self.table = OrderedDict(
            sorted(self.dic.items(),
                   key=lambda (k, v): (v, k),
                   reverse=True))
        self.list = self.check_allergies()

    def check_allergies(self):
        score = self.score
        allergies_list = []
        for product, value in self.table.iteritems():
            if score >= value:
                score -= value
                allergies_list.append(product)
        return list(reversed(allergies_list))

    def is_allergic_to(self, product):
        return product in self.list
