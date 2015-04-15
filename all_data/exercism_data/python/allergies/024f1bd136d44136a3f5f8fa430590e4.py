allergies = [
    ('eggs'	        , 1),  
    ('peanuts'		, 2),  
    ('shellfish'	, 4),  
    ('strawberries'	, 8),  
    ('tomatoes'		, 16), 
    ('chocolate'	, 32), 
    ('pollen'		, 64), 
    ('cats'	        , 128),
    ]

class Allergies():
    def __init__(self, allergy_total):
        self.list = [allergen for allergen, value in allergies if bool(value & allergy_total) == True]

    def is_allergic_to(self, item):
        return item in self.list
