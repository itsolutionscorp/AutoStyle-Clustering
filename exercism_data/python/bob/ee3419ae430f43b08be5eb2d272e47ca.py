#bob.py


def hey(qstn):
    qmark='?'    
    if qstn.strip()=='':
        return 'Fine. Be that way!'
    elif  qstn.isupper():
        return 'Whoa, chill out!'
    elif qmark in qstn[-1]:
        return 'Sure.'
    
    return "Whatever."
