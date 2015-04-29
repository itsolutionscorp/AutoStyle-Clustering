#-------------------------------------------------------------------------------
# Name:        module2
# Purpose:
#
# Author:      Mathman
#
# Created:     30/09/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

def main():
    pass
def distance(n,p):
    h=0
    a=n.replace('',' ')
    b=p.replace('',' ')
    l=a.split(' ')
    m=b.split(' ')
    for i in range(0,len(l)):
        if l[i]!=m[i]:
            h=h+1
    return h
if __name__ == '__main__':
    main()
