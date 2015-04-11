class Allergies():
   _ALLERGIES = ["cats", "pollen", "chocolate", "tomatoes", "strawberries", "shellfish", "peanuts", "eggs"] 
   def __init__(self, num):
       num = num % (2 ** len(self._ALLERGIES ))
       self.list = list()
       for i, allergie in enumerate(self._ALLERGIES):
           pot = 2 ** (len(self._ALLERGIES ) - 1 - i)
           if num - pot >= 0:
               num -= pot
               self.list.append(allergie)
       self.list.reverse() 
   def is_allergic_to(self, allergen):
       return allergen in self.list

a = Allergies(255)
print a.list
