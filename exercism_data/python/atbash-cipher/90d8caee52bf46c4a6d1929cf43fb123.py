#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Mathman
#
# Created:     10/10/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python
from string import translate
from string import maketrans
def main():
    pass
t=maketrans('abcdefghijklmnopqrstuvwxyz','zyxwvutsrqponmlkjihgfedcba')
def encode(s):
    s=s.replace(', ','')
    s=s.replace(' ','')
    s=s.lower()
#There's probably a better way to handle punctuation, but I don't know it yet
    s=s.strip('.')
    s=s.strip('?')
    s=s.strip('!')
    a=s.replace('',' ')
    l=a.split(' ')
    m=''
    for i in range (1,len(l)-1):
        m=m+l[i]
        if i%5==0 and i!=len(l)-2:
            m=m+' '
    m=translate(m,t)
    return m
def decode(m):
    m=m.replace(' ','')
    s=translate(m,t)
    return s
if __name__ == '__main__':
    main()
