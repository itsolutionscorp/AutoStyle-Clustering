#-------------------------------------------------------------------------------
# Name:        module2
# Purpose:
#
# Author:      Mathman
#
# Created:     06/10/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

def main():
    pass
def slices(a,n):
    b=a.replace('',' ')
    c=b.split()
    e=[]
    x=0
    if n>len(a) or n<=0:
        raise ValueError
    for i in range(len(c)+1-n):
        d=[]
        for j in range(n):
            d.append(int(c[x+j]))
        e.append(d)
        x=x+1
    return e

if __name__ == '__main__':
    main()
