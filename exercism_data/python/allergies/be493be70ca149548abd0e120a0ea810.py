__author__ = 'FlavioMiranda'
class Allergies(object):
    _scores = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

    def __init__(self,score):
        self.list = [allergie for index,allergie in enumerate(self._scores) if score & 1 << index]

    def is_allergic_to(self,key):
        return key in self.list
