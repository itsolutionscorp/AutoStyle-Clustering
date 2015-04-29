__author__ = 'jeffmarkey'

class Allergies:

    def __init__(self, number):
        all ={}
        all[0] = 'eggs'
        all[1] = 'peanuts'
        all[2] = 'shellfish'
        all[3] = 'strawberries'
        all[4] = 'tomatoes'
        all[5] = 'chocolate'
        all[6] = 'pollen'
        all[7] = 'cats'
        self.__allergy__ = all

        full = bin(number)
        count = 0
        list = []
        for elm in reversed(full):
            if((elm == "1") and (count<8)):
                list.append(all[count])
            count = count + 1
        self.list = list

    def is_allergic_to(self, value):
        if value in self.list:
            return True
        else:
            return False
