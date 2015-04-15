class SumOfMultiples:
    def __init__(self):
        pass
    def to(self, num):
        nums=0
        num=num-1
        while num>0:
            if num%3==0 or num%5==0:
                nums=nums+num
            num=num-1
        return nums
