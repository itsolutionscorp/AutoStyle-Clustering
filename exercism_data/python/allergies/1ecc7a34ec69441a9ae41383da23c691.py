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
        ls =[]
        p = 0
        for x in base_2_num[::-1]:
            if x =="1" and p < 8:
                ls.append(alergies[p])
            p += 1
#        print ls
        return  ls

    def is_allergic_to(self,item):
        return item in self.list

#print Allergies(5)
#print (toStr(1,2))
#allergies = Allergies(3)
#print (allergies.is_allergic_to('eggs'))
