def hey(s):
    s = unicode(s)
    has_alphas = bool(filter(unicode.isalpha, s))
    
    if s.isupper() and has_alphas:
        return "Whoa, chill out!"
    elif s.endswith('?'):
        return "Sure."
    elif not s or s.startswith(' ') and not has_alphas:
        return "Fine. Be that way!"
    else:
        return "Whatever."
