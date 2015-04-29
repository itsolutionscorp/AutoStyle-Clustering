def hey(q):
    ans = None
    if q.isspace() or not q:
        ans = 'Fine. Be that way!'
    elif q.isupper():
        ans = 'Whoa, chill out!'
    elif q[-1] == '?':
        ans = 'Sure.'
    else:
        ans = 'Whatever.'
    return ans
