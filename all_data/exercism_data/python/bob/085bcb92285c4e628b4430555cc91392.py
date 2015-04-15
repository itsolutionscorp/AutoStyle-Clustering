def hey(string):
    if not isinstance(string, basestring):
        return "Whatever."

    string = string.strip()
        
    if not string:
        return "Fine. Be that way!"
        
    if string.isupper():
        return "Whoa, chill out!"
       
    if string.endswith("?"):
        return "Sure."

    return "Whatever."
