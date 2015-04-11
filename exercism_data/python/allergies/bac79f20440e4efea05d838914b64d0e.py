#-------------------------------------------------------------------------------
# Name:        module2
# Purpose:
#
# Author:      Mathman
#
# Created:     02/10/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

def main():
    pass
class Allergies (object):
    def __init__(self,n):
        l=[]
        m=['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
        i=0
        n=n%256
        while n>0:
            if 1==n%2:
                l.append(m[i])
                n=n-1
            n=n/2
            i=i+1
        self.list=l
    def is_allergic_to(self,x):
        return self.list.__contains__(x)
if __name__ == '__main__':
    main()
