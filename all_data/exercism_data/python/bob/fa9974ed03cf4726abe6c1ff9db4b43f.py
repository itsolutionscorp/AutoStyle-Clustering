def hey(s):
    if s.isupper():
        answer = 'Whoa, chill out!'
    elif s.endswith('?'):
        answer = 'Sure.'
    elif s.isspace() or s == '':
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'
    return answer
