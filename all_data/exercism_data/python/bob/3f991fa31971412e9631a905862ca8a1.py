from string import ascii_letters as AL
from string import ascii_lowercase as LL
from unicodedata import normalize

def hey(s):
    s = normalize('NFKD',s.strip()).encode('ascii','ignore')
    if sum(1 for let in s if let in AL) > 0 and not any(let in LL for let in s):
        #at least one letter and none are lowercase
        return "Woah, chill out!"
    elif s.endswith("?"):
        return "Sure."
    elif s == '':
        return "Fine. Be that way!"
    else:
        return "Whatever."
