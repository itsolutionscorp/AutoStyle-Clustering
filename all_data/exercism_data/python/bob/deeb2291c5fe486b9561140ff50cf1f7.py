def hey(what):
    has_alpha = any(i.isalpha() for i in what)
    if has_alpha and all(i.isupper() for i in what[1:] if i.isalpha()):
        return 'Whoa, chill out!'
    if not what.strip():
        return 'Fine. Be that way!'
    if what.strip().endswith('?'):
        return 'Sure.'
    return 'Whatever.'
