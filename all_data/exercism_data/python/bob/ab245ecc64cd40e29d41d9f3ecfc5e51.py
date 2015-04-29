#Bob

def hey(s):
    s = s.replace(" ", "")
    if len(s) <= 1:
        return "Fine. Be that way!"
    elif s.isupper():
        return "Whoa, chill out!" 
    elif s[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
     
