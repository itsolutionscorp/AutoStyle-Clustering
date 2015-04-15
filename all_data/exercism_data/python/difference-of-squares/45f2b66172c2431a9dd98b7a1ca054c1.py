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
def square_of_sum(n):
    return ((n*(n+1))/2)**2
def sum_of_squares(n):
    return (n*(n+1)*(2*n+1))/6
def difference(n):
    return square_of_sum(n)-sum_of_squares(n)


if __name__ == '__main__':
    main()
