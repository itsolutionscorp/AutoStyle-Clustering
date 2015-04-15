'''exer sum_of_multiples'''

class SumOfMultiples:
    '''sum of multiples'''

    def __init__(self, *args):
        '''accept 1 or more ints as arguments or default to 3,5'''

        self.multiples = list(args) or [3, 5]


    def to(self, to_num):
        '''multiples to to_num'''

        mltpls_used = []    # don't add the same multiple twice, if common found
        result = 0
        for mltpl in self.multiples:
            cur_num = mltpl
            while cur_num < to_num:
                if cur_num not in mltpls_used:
                    result += cur_num
                    mltpls_used.append(cur_num)
                cur_num += mltpl

        return result
