def hey(string):
    input=string.strip()
    if input.isupper():
        return "Whoa, chill out!"
    elif input=='':
        return "Fine. Be that way!"
    elif input.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
