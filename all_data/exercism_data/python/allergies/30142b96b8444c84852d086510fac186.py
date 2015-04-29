class Allergies(object):
    _score_components = (('eggs',1), ('peanuts',2), ('shellfish',4),
        ('strawberries',8), ('tomatoes',16), ('chocolate',32), ('pollen',64),
        ('cats',128))
    
    def __init__(self, score):
        self.list = [item for item, value in self._score_components if not
            value & score == 0]

    def is_allergic_to(self, item):
        return (item in self.list)
