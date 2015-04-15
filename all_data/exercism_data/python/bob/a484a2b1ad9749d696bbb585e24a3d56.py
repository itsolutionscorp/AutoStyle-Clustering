def hey(string):

    if string.endswith("?") and not string.isupper():
        return 'Sure.'
    elif string.isupper():
        return 'Whoa, chill out!'
    elif not any(i.isalnum() for i in string):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
