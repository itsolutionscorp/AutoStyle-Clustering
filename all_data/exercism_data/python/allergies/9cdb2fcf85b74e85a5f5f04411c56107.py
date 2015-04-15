__author__ = 'banarasitippa'

def toStr(n,base):
   convertString = "0123456789ABCDEF"
   if n < base:
      return convertString[n]
   else:
      return toStr(n//base,base) + convertString[n%base]


class Allergies:

    def __init__(self,alergy_count):
        self.alergy_count = alergy_count
        self.list = self.findAlergies()

    def findAlergies(self):
        alergies = ('eggs peanuts shellfish strawberries tomatoes '
                 'chocolate pollen cats').split()
        base_2_num = toStr(self.alergy_count,2)
        return [allergen for (allergen, yesNo) in zip(alergies, base_2_num[::-1]) if int(yesNo)]


    def is_allergic_to(self,item):
        return item in self.list

#print Allergies(5)
#print (toStr(1,2))
#allergies = Allergies(3)
#print (allergies.is_allergic_to('eggs'))
