class Allergies(object):
    def __init__(self, score):
        self.score = score

    def is_allergic_to(self, food):
        return food in self.list

    @property
    def list(self):
        keys = sorted(Allergy.get.keys(), reverse = True)
        val = self.score
        allergies = []

        for key in keys:
            if not val: break
            if val < key: continue

            allergies.append(Allergy.get[key])
            val -= key

        return list(reversed(allergies))


def enum(*sequential, **named):
    values = {sequential[n]: 2 ** n for n in range(len(sequential))}
    reversed = {v: k for k, v in values.items()}
    values['get'] = reversed
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
