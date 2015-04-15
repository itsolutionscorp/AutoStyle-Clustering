allergydict = {
               1: 'eggs',
               2: 'peanuts',
               4: 'shellfish',
               8: 'strawberries',
               16: 'tomatoes',
               32: 'chocolate',
               64: 'pollen',
               128: 'cats'
               }


class Allergies():

    def __init__(self, score):

        # use bitwise AND (&) to check if corresponding bit is set or not
        self.list = [allergydict[num] for num in sorted(allergydict.keys()) 
                                          if (score & num) == num]

    def is_allergic_to(self, sensitizer):
        return sensitizer in self.list
