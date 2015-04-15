from itertools import imap
import operator

def distance(string1, string2):
    h1 = string1[0:len(min(string1, string2, key=len))]
    h2 = string2[0:len(min(string1, string2, key=len))]
    return sum(imap(operator.ne, h1, h2))
