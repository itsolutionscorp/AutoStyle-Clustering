class Allergies:
    def __init__(self,allergy_score):
        self.score = allergy_score
        allergy_score = allergy_score % 256
        self.list = []
        allergy_list = [('cats', 128),
                        ('pollen', 64),
                        ('chocolate', 32),
                        ('tomatoes', 16),
                        ('strawberries', 8),
                        ('shellfish', 4),
                        ('peanuts', 2),
                        ('eggs', 1)]
        for allergy in allergy_list:
            if allergy_score >= allergy[1]:
                self.list.insert(0,allergy[0])
            allergy_score = allergy_score % allergy[1]

    def is_allergic_to(self,allergen):
        return (allergen in self.list)

    
