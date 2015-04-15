class Allergies:
    allergy_list = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()
    def __init__(self, n):
        self.list = [self.allergy_list[i] for i in range(8) if (n>>i) & 1 != 0]

    def is_allergic_to(self,allergy):
        return allergy in self.list

print Allergies(166).list

#  Example 0:
#  166:  10100110
#  bin(166>>0): (where 0 is the list position of eggs in allergy_list)
#        10100110

#  bin(166>>0) & 1:
#        10100110
#        00000001
#        --------
#        00000000

# Therefore, eggs is not added to the list.

#  Example 1:
#  166:  10100110
#  bin(166>>1): 
#        01010011

#  bin(166>>1) & 1:
#        01010011
#        00000001
#        --------
#        00000001

# Therefore, peanuts is added to the list.
