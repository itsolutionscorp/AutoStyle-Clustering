class Allergies(object):
    
    def __init__(self,x):
        allergy_list = ['eggs','peanuts','shellfish','strawberries', \
                        'tomatoes','chocolate','pollen','cats']
        binx = bin(x)[2:]   #converts to binary string and strips '0b'
        binx = binx[::-1]   #reverse the order
        while len(binx)<8:  #pad with enough zeros to get to 8 bits
	    binx = binx + '0' 
	allergy_inds = filter(lambda i:binx[i]=='1',range(8))
	self.list = [allergy_list[i] for i in allergy_inds]
       
    def is_allergic_to(self,possible_allergens):
        #true if all the possible_allergens are in the list
        truthlist = []
        for i in possible_allergens.split():
            truthlist.extend([True] if i in self.list else [False])
        return all(truthlist)
