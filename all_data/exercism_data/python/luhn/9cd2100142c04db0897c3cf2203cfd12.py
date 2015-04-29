class Luhn(object):
    def __init__(self, num=0):
        self.num = num
    
    # from right to left process every other number
    def addends(self):
        # convert to list and reverse
        lst = list(str(self.num))
        lst.reverse()
        # iterate over each element in lst
        for i in range(len(lst)):
            # convert to int
            lst[i] = int(lst[i])
            # on every second element
            if i % 2 == 1:
                # mutiply by 2
                lst[i] *= 2
                # if greater than 10
                if lst[i] / 10 > 0:
                    # subtract 9
                    lst[i] -= 9
        # flip the list back       
        lst.reverse()
        return lst
    
    # sum the numbers from addends and return modulo 10
    def checksum(self):
        return sum(self.addends()) % 10
        
    # if divisible by 10, return True    
    def is_valid(self):
        return self.checksum() == 0

    # given a bad_num determine the correct number to make the check pass
    @staticmethod
    def create(bad_num):
        for i in range(10):
            test_num = int(str(bad_num) + str(i))
            if Luhn(test_num).is_valid():
                return test_num
