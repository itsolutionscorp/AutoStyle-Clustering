class Allergies():
    
    item_scores = {
        "eggs": 1,
        "peanuts": 2,
        "shellfish": 4,
        "strawberries": 8,
        "tomatoes": 16,
        "chocolate": 32,
        "pollen": 64,
        "cats": 128,
    }
    
    #only exists to inforce the order required in the tests.
    items = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']

    def __init__(self, score):
        self.score = score
        self.list = []
        self.list_allergies()
                

    def is_allergic_to(self, food_item):
        return food_item in self.list

    """initiate the list field of this instance with this instance's allergies"""
    def list_allergies(self):
        #helper function returns true if score ==> allergic to food_item
        def rec_allergie(food_item, score):
            is_allergic = False
            #step-forward
            val = self.item_scores.pop(food_item, None)
            score -= val
            
            if score == 0:
                is_allergic = True
            
            elif score > 0:
                for food in self.item_scores:
                    if not(is_allergic):
                        is_allergic = rec_allergie(food, score)
            #backtrack                                                       
            score += val
            self.item_scores[food_item] = val
            
            return is_allergic
        
        for item in self.items:
            allergic = rec_allergie(item, self.score)
            if allergic:
                self.list.append(item)
                
        return 
