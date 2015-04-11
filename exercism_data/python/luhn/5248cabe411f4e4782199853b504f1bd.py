class Luhn:
    
    def __init__(self,Number=0):
        self.Number = Number
        
    def ListMake(self):
        return list(map(int,list(str(self.Number))))
        
    def addends(self):
        NumList = self.ListMake()
        for i in range(-2,-(len(NumList)+1),-2):
            if NumList[i] < 5:
                NumList[i] *= 2
            else:
                NumList[i] = NumList[i]*2 - 9
        return NumList
        
    def checksum(self):
        return (sum(self.addends())) % 10
        
    def is_valid(self):
        return self.checksum() == 0
        
    def create(n):
        NumList2 = list(map(int,list(str(n))))
        for i in range(-1,-(len(NumList2)+1),-2):
            if NumList2[i] < 5:
                NumList2[i] *= 2
            else:
                NumList2[i] = NumList2[i]*2 - 9
        NumberToAdd = (10-(sum(NumList2) % 10)) % 10
        NumList3 = list(map(int,list(str(n))))
        NumList3.append(NumberToAdd)
        return int("".join(list(map(str,NumList3))))
