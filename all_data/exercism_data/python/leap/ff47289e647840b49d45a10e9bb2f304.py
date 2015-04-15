#-------------------------------------------------------------------------------
# Name:        year
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
def is_leap_year(n):
    if n%4 != 0:
        return False
    elif n%400 == 0:
        return True
    elif n%100 == 0:
        return False
    else:
        return True
if __name__ == '__main__':
    main()
