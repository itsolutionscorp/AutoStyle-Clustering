class Allergies():
    def __init__(self,num):
        self.num=num
        self.dic={0:"",1:"eggs",2:"peanuts",4:"shellfish",8:"strawberries",
         16:"tomatoes",32:"chocolate",64:"pollen",128:"cats"}
        self.binary=([int(x) for x in bin(num)[2:]])[::-1]

        self.basket=[self.dic.get(self.binary[m]*(2**m),"") for m in range(len(self.binary))]
        self.list=[item for item in self.basket if item]
    def is_allergic_to(self,food):
        return food in self.list





        


    
