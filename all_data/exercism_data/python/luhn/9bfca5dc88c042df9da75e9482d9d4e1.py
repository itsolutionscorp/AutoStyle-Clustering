""" module luhn for exercism.io programming training """

class Luhn():
    """ class Luhn used to use the luhn number check algorithm """
    def __init__(self, number):
        self.number = str(number)
    
    
    def addends(self):
        """ calculates luhn number """
        def one(k):
            return int(k) if int(k) < 10 else int(k) - 9
            
        return [one(2*int(j)) if ((i + len(self.number)) % 2 == 0) else int(j) for i, j in enumerate(self.number)]
    
    
    def checksum(self):
        """ calculates checksum from luhn number """
        return sum(self.addends()) % 10
    
    
    def is_valid(self):
        """ checks if a number is valid with the luhn algorithm """
        return self.checksum() == 0
    
    
    @staticmethod
    def create(number):
        """ calculates which digit to append to a number to make it a valid in the luhn algorithm """
        new_luhn = Luhn(str(number) + '0')
        check = (10 - new_luhn.checksum()) % 10
        return int(str(number) + str(check))
