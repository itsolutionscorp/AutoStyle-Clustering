class Luhn(object):

    @staticmethod
    def create(num):
        
        for i in range(10):
            testnum = int(str(num)+str(i))
            
            if Luhn(testnum).is_valid():
                return testnum


            
    def __init__(self,num):
        self.number = num
        

    def addends(self):
        digits = map(int,str(self.number))
        digits.reverse()

        for i in range(0,len(digits)):
            if i%2==1:
                digits[i] *=2
                if digits[i] >=10:
                    digits[i]-=9
        digits.reverse()
        return digits


    def checksum(self):
        return sum(self.addends())%10


    def is_valid(self):
        
        return sum(self.addends())%10==0
