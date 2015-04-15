class Allergies:
    allergies = [
        "eggs", "peanuts",
        "shellfish", "strawberries",
        "tomatoes", "chocolate",
        "pollen", "cats"
    ]
    
    def __init__(self, num):
        ### Get the thing in order
        self.num = bin(num)[2:][::-1]
        self.list = []
        length = min(len(self.num), 8)
        
        for i in range(length):
            if int(self.num[i]):
                self.list.append(self.allergies[i])
    
    def is_allergic_to(self, allergy):
        return self.list.count(allergy)

print Allergies(0).list
