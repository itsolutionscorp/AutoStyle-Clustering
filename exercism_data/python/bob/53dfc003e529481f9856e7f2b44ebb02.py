def hey(s):
    s = s.strip()
    if not s:
        return 'Fine. Be that way!'
    if s == s.upper() != s.lower():
        return 'Whoa, chill out!'
    if s[-1] == '?':
        return 'Sure.'
    return 'Whatever.'