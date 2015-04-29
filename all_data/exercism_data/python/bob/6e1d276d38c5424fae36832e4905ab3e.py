def hey(string):

    # If you don't say anything to Bob...
    if string.strip() == '':
        # Here, we're stripping out any whitespace around the string.
        # If the string consists solely of whitespace, we get an empty string.
        return 'Fine. Be that way!'

    # If you yell at Bob...
    if string.isupper():
        # So yeah, isupper() is a lifesaver here. Originally I was comparing the
        # given string against an upper() version of itself, but that would
        # match strings which did not contain any alpha characters. Much
        # additional code ensued. The isupper() function returns true if there
        # is at least one alphacharacter, and all alpha characters in the string
        # are uppercase.
        return 'Whoa, chill out!'
    
    # If you ask Bob a queestion...
    if string.endswith('?'):
        # Questions end with question marks, right? A yelled question counts as
        # yelling though, and yelling was taken care of above.
        return 'Sure.'
    
    # If you say anything else to Bob...
    return 'Whatever.'
