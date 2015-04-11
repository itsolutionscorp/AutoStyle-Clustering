def hey(what):
    what = what.strip(' \t')
    if len(what) == 0:
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[-1]=='?':
        return 'Sure.'  
    else:
        return 'Whatever.'
