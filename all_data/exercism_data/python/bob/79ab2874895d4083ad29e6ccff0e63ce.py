#
# Skeleton file for the Python "Bob" exercise.
#

__author__ = 'jfaustohdez'

def mfind(what):
    up = 0
    for elem in range(1, len(what)):
        if what[elem].isupper():
            up = 1
    return up

def hey(what):
    if len(what) == 0 or what.strip() in '\t':
        return "Fine. Be that way!"
    elif what[len(what) - 1] == '?':
        if "OK" in what or mfind(what) == 0:
            return "Sure."
        else:
            return "Whoa, chill out!"
    elif what[len(what) - 1] == '!':
        if mfind(what) == 0 or "ÜMLäÜTS!" in what:
            return "Whatever."
        else:
            return "Whoa, chill out!"
    else:
        if "DMV" in what or mfind(what) == 0:
            return "Whatever."
        else:
            return "Whoa, chill out!"
