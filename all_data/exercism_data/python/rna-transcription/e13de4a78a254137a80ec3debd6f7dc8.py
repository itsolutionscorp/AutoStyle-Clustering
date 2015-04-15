#-------------------------------------------------------------------------------
# Name:        dna
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
def to_rna(n):
    n=n.replace('A','U')
    n=n.replace('T','A')
    n=n.replace('G','T')
    n=n.replace('C','G')
    n=n.replace('T','C')
    return n
if __name__ == '__main__':
    main()
