def hey(s):
    ans = None
    if s.isspace() or not s:
        ans = 'Fine. Be that way!'
    elif s.isupper():
        ans = 'Whoa, chill out!'
    elif s[-1] == '?':
        ans = 'Sure.'
    else:
        ans = 'Whatever.'
    return ans
