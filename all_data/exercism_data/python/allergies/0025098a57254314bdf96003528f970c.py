class Allergies(object):
    def __init__(self, score):
        self.score = score
        self.list = [Allergy.get[allergie] for bit, allergie in zip(reversed(format(self.score, '08b')), sorted(Allergy.get)) if bit == '1']

    def is_allergic_to(self, food):
        return food in self.list

def enum(*sequential, **named):
    values = {sequential[n]: 2 ** n for n in range(len(sequential))}
    values['get'] = {v: k for k, v in values.items()}
    return type('Enum', (), values)

Allergy = enum(
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats'
)
