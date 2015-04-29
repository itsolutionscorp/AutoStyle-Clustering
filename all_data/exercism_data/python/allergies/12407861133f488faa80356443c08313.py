class Allergies(list):

    def __init__(self, score):
        items = [   'eggs',\
                    'peanuts',\
                    'shellfish',\
                    'strawberries',\
                    'tomatoes',\
                    'chocolate',\
                    'pollen',\
                    'cats'  ]
        self.list = []
        for i in range(8):
            if (1 << i) & score:
                self.list.append(items[i])

    def is_allergic_to(self, item):
        return item in self.list
