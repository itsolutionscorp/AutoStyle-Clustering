#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    punct = '!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~'
    what = what.replace(" ", "").strip()
    whatnopunct = ''.join(ch for ch in what if ch not in punct)
    if what == "":
        return "Fine. Be that way!"
    elif (what.upper() == what and not whatnopunct.isdigit()) or (whatnopunct.isdigit() and what[-1] == "!"): 
        return "Whoa, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
