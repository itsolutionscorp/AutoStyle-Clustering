def hey(what):
    if what == '':
        print "Fine. Be that way!"
    elif what[-1] == '?':
        print "Sure."
    elif what.isupper():
        print "Woah, chill out!"
    else:
        print "Whatever."
