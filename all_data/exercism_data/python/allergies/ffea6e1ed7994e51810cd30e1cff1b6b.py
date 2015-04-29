class Allergies:
    allergies = []

    def __init__(self, number):
        allergy_values = [ 
                ('cats',128), 
                ('pollen',64),
                ('chocolate',32),
                ('tomatoes',16),
                ('strawberries',8),
                ('shellfish',4),
                ('peanuts',2),
                ('eggs',1),
        ]
        self.list = []
        number = number%256
        for (n, v) in allergy_values:
            if number >= v:
                self.list.insert(0,n)
                number = number-v

    def is_allergic_to(self, name):
        return name in self.list
