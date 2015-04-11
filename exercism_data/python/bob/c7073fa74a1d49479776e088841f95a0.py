#!/usr/bin/env python

def hey(input):
    if input.isspace() or input == '':
        return "Fine. Be that way!"
    elif input.isupper():
        return "Whoa, chill out!"
    elif input[-1] == '?':
        return "Sure."
    else:
        return "Whatever."
        
if __name__ == '__main__':
    import sys
    
    if len(sys.argv) != 2:
        print "Usage: ./bob '<message>'"
        sys.exit()
    else:
        print hey(sys.argv[1])
