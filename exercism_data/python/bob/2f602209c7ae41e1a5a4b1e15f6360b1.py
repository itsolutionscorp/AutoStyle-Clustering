def hey(what):
    what = what.strip()
    if what == "":
        return "Fine. Be that way!"

    def lastChar(string): return string[len(string)-1]
    char = lastChar(what);
    
    if what.isupper():
        return "Whoa, chill out!"
    if char == '?':
        return "Sure."
    else:
        return "Whatever."
