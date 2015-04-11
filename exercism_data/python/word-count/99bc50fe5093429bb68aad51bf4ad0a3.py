#-------------------------------------------------------------------------------
# Name:        wordcount
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
def word_count(n):
    n=n.replace('\n',' ')
    a=[]
    l=n.split(' ')
    for i in range (0,len(l)):
        if (a.__contains__(l[i]) == False) and l[i]!='':
            a.append(l[i])
    d={x: l.count(x) for x in a}
    return d
if __name__ == '__main__':
    main()
