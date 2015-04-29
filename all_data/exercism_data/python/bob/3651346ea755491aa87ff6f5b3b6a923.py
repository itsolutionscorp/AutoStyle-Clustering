#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Mathman
#
# Created:     27/09/2014
# Copyright:   (c) Mathman 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

def main():
    pass

def hey(x):
    if x.isupper() == True:
        return "Whoa, chill out!"
    elif x.endswith ('?') == True:
        return "Sure."
    elif (x.isspace() == True) or (x==''):
        return "Fine. Be that way!"
    return "Whatever."

if __name__ == '__main__':
    main()
