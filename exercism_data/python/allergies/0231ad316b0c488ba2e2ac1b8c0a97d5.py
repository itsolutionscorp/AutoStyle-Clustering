allergyTypes = {
    1:"eggs",
    2:"peanuts",
    4:"shellfish",
    8:"strawberries",
    16:"tomatoes",
    32:"chocolate",
    64:"pollen",
    128:"cats"
 }


class Allergies:
    
    def __init__(self, bitmask):
        self.bitmask = bitmask
        self.list = [allergyTypes[i] for i in [1,2,4,8,16,32,64,128] if i & bitmask > 0]
        

    def is_allergic_to(self, risk):
       return  risk in self.list

print( Allergies(25).list)
