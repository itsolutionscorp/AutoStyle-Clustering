def hey(string):
    if not isinstance(string, basestring):
        return "Whatever."

    string = string.lstrip().rstrip()
        
    if not string:
        return "Fine. Be that way!"
        
    if string.isupper():
        return "Whoa, chill out!"
       
    if string[-1] == "?":
        return "Sure."

    return "Whatever."
