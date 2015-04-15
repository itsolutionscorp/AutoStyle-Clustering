class Allergies:

    def __init__(self, allergy_score):
        
        value_to_allergy= {1:'eggs', 2:'peanuts', 4:'shellfish', 8:'strawberries',
                16:'tomatoes', 32:'chocolate', 64:'pollen', 128:'cats'}

        self._score = allergy_score
        self._allergies = []

        #create list of decreasing allergy_values
        allergy_values = sorted(value_to_allergy.keys(), reverse = True)

        #check for bad score (over sum of numbers and odd)
        if self._score > sum(allergy_values) and self._score % 2 == 1:
            self._allergies = [value_to_allergy[1]]
            self.list = [value_to_allergy[1]]
        else:
            #check if value holds allergy 
            for value in allergy_values:
                if self._score - value >= 0:
                    self._allergies.insert(0, value_to_allergy[value])
                    self._score = self._score - value
            self.list = self._allergies

    def is_allergic_to(self, item_to_test):
        """checks if alergic to thing"""
        if item_to_test in self._allergies:
            return True
        else:
            return False
