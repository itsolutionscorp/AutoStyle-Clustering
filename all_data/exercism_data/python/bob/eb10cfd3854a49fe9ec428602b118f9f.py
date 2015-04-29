def hey(saying=" "):
    if saying=="" or saying[0] == " " and saying[-1:] == "" or saying[-1:] == "\t":
        return unicode('Fine. Be that way!')
    elif saying.isupper():
        return unicode("Whoa, chill out!")
    elif saying[-1:] == "?":
        return unicode("Sure.")
    else:
        return unicode("Whatever.")

if __name__ == "__main__":
    import sys
    print "test"
    print hey(sys.argv[1])
