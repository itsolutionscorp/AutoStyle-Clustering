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
def letter_count(w):
    h=0
    b=[]
    n=w.replace('',' ')
    m=n.split(' ')
    for i in range (0,len(m)):
        if (b.__contains__(m[i]) == False) and m[i]!='':
            b.append(m[i])
    d={x: m.count(x) for x in b}
    return d
def detect_anagrams(a,l):
    c=[]
    for i in range (0,len(l)):
        if letter_count(a.lower())==letter_count(l[i].lower()) and a.lower()!=l[i].lower():
            c.append(l[i])
    return c
if __name__ == '__main__':
    main()
