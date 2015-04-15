class Allergies(object):
    def __init__(self, score):
        score = score % 256
        itemlist = ('cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs')
        valuelist = (128, 64, 32, 16, 8, 4, 2, 1)
        self.list = []
        for (item, value) in zip(itemlist, valuelist):
            if score >= value:
                score -= value
                self.list.append(item) 
        self.list.reverse()

    def is_allergic_to(self, allergen):
        return bool(self.list.count(allergen))
