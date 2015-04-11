class Allergies:
    def __init__(self, index):
        index = index % 256
        self.list = []
        products = ["eggs","peanuts","shellfish","strawberries","tomatoes",
                "chocolate","pollen","cats"]
        products_values = [(x,2**products.index(x)) for x in products]
        products_values.reverse()
        for p in products_values:
            if p[1] <= index:
                index -= p[1]
                self.list.append(p[0])
        self.list.reverse()
            
        
    def is_allergic_to(self, product):
        return product in self.list


print Allergies(255).list
        
    
