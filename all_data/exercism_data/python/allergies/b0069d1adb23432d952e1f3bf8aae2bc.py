# return an integer as reversed binary in string form
def rev_binary_string(n):
    return [2 ** exponent
            for exponent, bit in enumerate(bin(n)[:1:-1])
            if bit == '1']

# main class to manage allergies
class Allergies(object):
    
    # allergy list
    __allergies = {2 ** n: allergy_list
                  for n, allergy_list in enumerate(
                  ("eggs peanuts shellfish strawberries tomatoes" + 
                  " chocolate pollen cats").split())}
    
    def __init__(self, allergy_score=0):
        # initialize allergy score
        self.allergy_score = allergy_score
        
        # initialize the list of allergies in allergy score
        self.list = [self.__allergies[p]
                     for p in rev_binary_string(allergy_score)
                     if p in self.__allergies]
    
    def is_allergic_to(self, allergy):
        return allergy in self.list

if __name__ == '__main__':
    al = Allergies(255)
    print al.list
