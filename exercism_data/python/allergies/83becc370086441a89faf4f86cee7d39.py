from collections import OrderedDict

allergy_list = OrderedDict([
    ('eggs', 1),
    ('peanuts', 2),
    ('shellfish', 4),
    ('strawberries', 8),
    ('tomatoes', 16),
    ('chocolate', 32),
    ('pollen', 64),
    ('cats', 128)
])

class Allergies():
    def __init__(self, allergy_number):
        self.list = []

	for allergy, code in allergy_list.iteritems():
            if code & allergy_number:
                self.list.append(allergy)
        
    def is_allergic_to(self, allergy):
        return allergy in self.list
