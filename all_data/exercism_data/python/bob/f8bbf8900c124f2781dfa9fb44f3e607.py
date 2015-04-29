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

def hey(n):
    if n.isupper() == True:
        return "Whoa, chill out!"
    elif n.endswith ('?') == True:
        return "Sure."
    elif n.isspace() == True:
        return "Fine. Be that way!"
    # this case does not cover the empty string,
    # so I handle it in the next branch:
    elif n=='':
        return "Fine. Be that way!"
    else:
        return "Whatever."

if __name__ == '__main__':
    main()
