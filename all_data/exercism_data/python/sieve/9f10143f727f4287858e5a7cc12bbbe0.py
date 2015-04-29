#-------------------------------------------------------------------------------
# Name:        module2
# Purpose:
#
# Author:      Mathman
#
# Created:     07/10/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python
from math import sqrt
from math import floor
def main():
    pass
def sieve(n):
    l=[]
    primes=[]
    for i in range (1,n):
        l.append(i)
    l[0]=0
    for j in range (2,int(floor(sqrt(n)))+1):
        for k in range (0,n-1):
            if l[k]%j ==0 and l[k]!=j:
                l[k]=0
    for x in range(1,len (l)):
        if l[x-1]!=0:
            primes.append (l[x-1])
    return primes
if __name__ == '__main__':
    main()
