class SumOfMultiples():
    def __init__(self, *nums):

        if nums == ():
            nums = (3,5)

        self.nums = nums

    def to(self, max):
        tot_list = []
        tot = 0

    """ Take every argument, and construct a list of multiples less than
        max. 
    """ 
        for num1 in self.nums:
           for num2 in range(max):
              if num2 % num1 == 0:
                  tot_list.append(num2)

    """ Remove duplicate values found in the constructed list.     

        num_list = set(tot_list)
        
        for num in num_list:
            tot += num
        return tot 
