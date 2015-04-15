__author__ = 'jeffmarkey'

class SumOfMultiples:


    def __init__(self, mult1=3, mult2=5, mult3=0):
        self.__list___ = []
        self.__list___.append(mult1)
        self.__list___.append(mult2)
        self.__list___.append(mult3)
        pass

    def to(self, number):
        sum = 0
        for x in range(number):
            add_number = False
            for elm in self.__list___:
                try:
                    if(x % elm == 0):
                        add_number = True
                except:
                    pass
            if(add_number):
                sum = sum + x

        return sum
