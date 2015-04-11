__author__ = 'jimblackler'


class Allergies(object):
    def __init__(self, mask):
        self.list = []
        if mask & 1:
            self.list.append('eggs')
        if mask & 2:
            self.list.append('peanuts')
        if mask & 4:
            self.list.append('shellfish')
        if mask & 8:
            self.list.append('strawberries')
        if mask & 16:
            self.list.append('tomatoes')
        if mask & 32:
            self.list.append('chocolate')
        if mask & 64:
            self.list.append('pollen')
        if mask & 128:
            self.list.append('cats')

    def is_allergic_to(self, name):
        return name in self.list
