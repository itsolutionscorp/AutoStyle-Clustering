ALLERGIES = ['eggs',
             'peanuts',
             'shellfish',
             'strawberries',
             'tomatoes',
             'chocolate',
             'pollen',
             'cats']


class Allergies(object):

    def __init__(self, score):
        self.list = []
        while score > 256:
            score -= 256
        as_bin = list("{:08b}".format(score))
        as_bin.reverse()
        for name, val in zip(ALLERGIES, as_bin):
            if val == '1':
                self.list.append(name)

    def is_allergic_to(self, name):
        return name in self.list
