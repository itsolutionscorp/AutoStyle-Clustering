def hey(string):
    input=string
    if input.isupper():
        return "Whoa, chill out!"
    elif input.isspace()==True or input=='':
        return "Fine. Be that way!"
    elif input.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
