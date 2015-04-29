def hey(astring):

    if astring.isupper():
        return 'Whoa, chill out!'

    elif astring.endswith('?'):
        return 'Sure.'

    elif not (any([chr.isalpha() for chr in astring]) or any([chr.isdigit() for chr in astring])):
        return 'Fine. Be that way!'

    else:
        return 'Whatever.'
