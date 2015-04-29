class Luhn:
    def __init__(self,int):
        self.int = int

    def addends(self):
        l = [int(x) for x in str(self.int)]
        newl = list()
        for cnt,i in enumerate(reversed(l)):
            temp = l[len(l)-cnt-1]
            if cnt>0 and cnt%2==1:
                temp *= 2
                if temp>9:
                    temp -= 9
                newl.insert(0,temp)
            else:
                newl.insert(0,temp)
        return newl
    
    def checksum(self):
        #return the last digit of the sum of the list elements
        return(int(str(sum(self.addends()))[-1]))

    def is_valid(self):
        if self.checksum()==0:
            return True
        else:
            return

    def create(int):
        #we need to account for the last position that's missing
        #so im multiplying by 10
        tmp = int*10
        last_dig = Luhn(tmp).checksum()
        if last_dig == 0:
            return tmp
        
        return tmp+10-last_dig
