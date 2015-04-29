from string import digits, punctuation, whitespace

# use sets for quicker lookups
digits = set(digits)
punctuation = set(punctuation)
whitespace = set(whitespace)

def containsLowercaseLetters(text):
    for letter in text:
        if letter.islower():
            return True
    return False

def containsNoLetters(text):
    numdigs = [l for l in text if l in digits or l in punctuation or l in whitespace ]
    return len(numdigs) == len(text)

def hey(what):
    #strip whitespace
    what = what.strip()

    #if empty message
    if what == "":
        return "Fine. Be that way!"

    #a shout contains no lowercase letters but isn't just numbers
    if not containsLowercaseLetters(what) and not containsNoLetters(what):
        return "Whoa, chill out!"

    #question ends in questionmark
    if what[-1] == "?":
        return "Sure."

    #if nothing else, return whatever
    return "Whatever."
