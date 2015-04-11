import copy

class SumOfMultiples():
    
    def __init__(self, *args):
        if len(args) == 0:
            self.baseNums = (3,5)
        else:
            self.baseNums = args
    
    def to(self, to):
        '''
        I will make use of the neat formula to compute the sum of all numbers up to n:
        sum(n) = n*(n+1)/2
        If you want to compute the sum of all multiples of a up to n the formula changes 
        to: sum(a,n) = a*(n/a)(n/a+1)/2
        I compose the sum of all multiples of more than one base number as follows:
        Let's say we want to compute the sum of the multiples of a,b, and c up to n.
        We start by adding the sum of the multiples of a to the sum of the multiples of b
        to the sum of the multiples of c.
        Unfortunately we now have added some elements more than once. For instance we added
        a*b and b*a. Thus we have to subtract the sum of the multiples of a*b, a*c, and b*c.
        Bugger, now we have taken away to much, e.g., we had 2 a*b*c in the sum and we took 
        away 3 a*b*c. Ergo we have to add the sum of multiples of a*b*c.
        '''
        result = 0
        curRow = list(self.baseNums)
        for i in xrange(len(self.baseNums)):           
            for num in curRow:
                if i%2 == 0:
                    result += self.sumOfMul(num, to)
                else:
                    result -= self.sumOfMul(num, to)
            #compute the next row
            curRow = map(self.mulBaseNums, (x for x in self.subsetsOfSize(i+2, len(self.baseNums))))
        return result
        
    def sumOfMul(self, num, to):
        ''' computes the sum of the multiples of 'num' in the natural numbers smaller than 'to' '''
        n = (to-1)/num
        return num*n*(n+1)/2

    def mulBaseNums(self, l):
        '''multiplies the numbers of self.baseNums indexed by list l'''
        r = 1
        for el in l:
            r *= self.baseNums[el]
        return r
    
    def subsetsOfSize(self, k, n):
        '''generator that returns all subsets of size k of the numbers 0 .. n-1 
           For example: subsetOfSize(2,4) returns [0,1], [0,2], [0,3], [1,2], [1,3], [2,3]'''
        if k>n:
            yield []
            return
        curSubset = [x for x in xrange(k)]
        yield curSubset
        try:
            while True:
                curSubset = self.nextSubset(curSubset,n)
                yield curSubset
        except:
            #done
            pass
    
    def nextSubset(self, subset, n):
        '''helper function for subsetsOfSize(k,n)'''
        k = len(subset)
        subset = copy.copy(subset)
        # look for an index that can be incremented
        for i in range(k-1, -1, -1):
            # can this index be incremented?
            if subset[i] < n - k + i:
                # increment it
                subset[i] +=1
                # reset all numbers after i
                for j in range(i+1, k):
                    subset[j] = subset[j-1] + 1
                return subset
            else:
                if i == 0:
                    raise Exception("Done")
