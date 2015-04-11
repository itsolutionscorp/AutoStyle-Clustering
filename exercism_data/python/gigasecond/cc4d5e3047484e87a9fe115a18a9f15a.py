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
from datetime import date
from datetime import timedelta
def main():
    pass
def add_gigasecond(d):
    t=timedelta(seconds=1000000000)
    d=d+t
    return d
if __name__ == '__main__':
    main()
