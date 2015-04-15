class Luhn:

    def __init__(self, num):
        self.num=num

    def addends(self):
        list_of_numstrings=list(str(self.num))
        list_of_nums=[]
        numlist=[] 
        for number in list_of_numstrings:
            list_of_nums.append(int(number))
        counter=-1
        test=True
        while test==True:
            if counter%2==0:
                try:
                    doubled=list_of_nums[counter]
                    doubled=doubled*2
                    while doubled>9:
                        doubled=doubled-9
                    numlist.insert(0, doubled)
                except:
                    test=False
            else:
                try:
                    numlist.insert(0, list_of_nums[counter])
                except:
                    test=False
            counter=counter-1
        return numlist

    def checksum(self):
        ends_added=self.addends()
        total_sum=0
        for number in ends_added:
            total_sum=total_sum+number
        return int(list(str(total_sum))[-1])
    def is_valid(self):
        if self.checksum()==0:
            return True
        else:
            return False

    @staticmethod
    def create(num):
        for number in [1,2,3,4,5,6,7,8,9,0]:
            if Luhn(int(str(num)+str(number))).is_valid()==True:
                return int(str(num)+str(number))
        return int(str(num)+str(number))
            
            
