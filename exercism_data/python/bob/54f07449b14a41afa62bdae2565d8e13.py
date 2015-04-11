def hey(what):
    what = what.strip()
    if (nothing_said(what)):
        return 'Fine. Be that way!'
    if (screamed(what)):
        return 'Whoa, chill out!'
    if (asked(what)):
        return 'Sure.'
    return 'Whatever.'

def nothing_said(words):
    return len(words) == 0

def screamed(words):
    return words.isupper() and has_alphabetical_char(words)

def asked(words):
    return words[-1] == '?'

def has_alphabetical_char(words):
    return any(ord(c) in range(65,90) or ord(c) in range(97,122) for c in words)
