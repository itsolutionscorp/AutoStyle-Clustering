class SumOfMultiples(object):
    # allow 0 - 3 arguments
    def __init__(self, a=3, b=5, c=0):
        self.multiples = [a, b, c] 
    
    def to(self, max_range):
        result_set = set()
        for i in range(1, max_range):
            for j in self.multiples:
                if j != 0 and i % j == 0:
                    result_set.add(i)

        sum_of_result_set = 0
        for num in result_set:
            sum_of_result_set += num
        return sum_of_result_set
