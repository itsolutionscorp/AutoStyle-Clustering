#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #make sure you are using ascii characters.
    print what
    if what.strip().endswith("?") and what != what.upper() or what.strip().endswith("?") and what == what.lower():
        return "Sure."
    if what.strip().endswith("!") and what == what.upper() and what != what.lower() or what == what.upper() and what != what.lower():
        return "Whoa, chill out!"
    if what.strip() == '':
        return "Fine. Be that way!"
    else:
        return "Whatever."
    return
