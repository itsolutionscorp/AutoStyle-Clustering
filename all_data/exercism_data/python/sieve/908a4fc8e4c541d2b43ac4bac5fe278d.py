def sieve(n):

    nList = range(2,n+1)
    pList = []

    while len(nList):

        pList += [nList[0]]
        nList = filter(lambda x: isNotDivisible(x,nList[0]), nList)
        
    return pList

def isNotDivisible(x,y):
    '''Returns bool(x is not divisible by y)'''

    return bool(x % y)
