from collections import OrderedDict

class Allergies(object):

    def __init__(self, allergy_score):
        self.allergy_score = allergy_score
        self.allergy_value_dict = OrderedDict([
            ('eggs', 1),
            ('peanuts', 2),
            ('shellfish', 4),
            ('strawberries', 8),
            ('tomatoes', 16),
            ('chocolate', 32),
            ('pollen', 64),
            ('cats', 128)
            ])
        self.list = self.list_allergens()

    def list_allergens(self):
        result = []
        for key, value in self.allergy_value_dict.iteritems():
            if self.allergy_score & value == value:
                result.append(key)
        return result


    def is_allergic_to(self, food):
        if self.allergy_score & self.allergy_value_dict[food] == self.allergy_value_dict[food]:
            return True
        else:
            return False
