from collections import OrderedDict

class Allergies(object):
    allergy_masks = OrderedDict(sorted({"cats":128, "pollen":64, "chocolate":32, "tomatoes":16, "strawberries":8, "shellfish":4, "peanuts":2, "eggs":1}.items(), key=lambda t: t[1]))

    def __init__(self, allergymap):
        self.list = [item for item in self.allergy_masks if self.allergy_masks[item] & allergymap]

    def is_allergic_to(self, item):
        return item in self.list
