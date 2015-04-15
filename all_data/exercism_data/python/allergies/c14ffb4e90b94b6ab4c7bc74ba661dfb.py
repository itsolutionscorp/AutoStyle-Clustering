class Allergies(object):

    def __init__(self, value):
        dictAllergy = {
            1 : 'eggs',
            2 : 'peanuts',
            4 : 'shellfish',
            8 : 'strawberries',
            16 : 'tomatoes',
            32 : 'chocolate',
            64 : 'pollen',
            128 : 'cats'
        }

        #unsorted allergen list
        self.listIndex = []

        #if value is greater than sum of dict keys
        if value > sum(dictAllergy.keys()):
            self.list = ([dictAllergy[1]])
        else:
            #Sort dict keys in descending order for looping from largest to smallest
            checkInt = sorted([i for i in dictAllergy.keys()], reverse = True)
            # #Loop through the dictionary as long as the value is greater than 0
            # while value > 0:
            #loop through keys to find the largest that is less than value
            for i in checkInt:
                if i <= value:
                    #Add the allergen to the list to be returned
                    self.listIndex.append(i)
                    #subtract the from value to find the next largest value that is less than value
                    value = value - i
            self.list = [dictAllergy[j] for j in sorted(self.listIndex)]

    def is_allergic_to(self, checkAllergen):
        #check if the allergen in the parameter list is in the allergen list
        return checkAllergen in self.list


if __name__ == '__main__':
    print(Allergies(257).list)
    dictAllergy = {
            1 : 'eggs',
            2 : 'peanuts',
            4 : 'shellfish',
            8 : 'strawberries',
            16 : 'tomatoes',
            32 : 'chocolate',
            64 : 'pollen',
            128 : 'cats'
        }
    print(sum(dictAllergy.keys()))
