def hey(string):

    # Strip whitespace from the incoming string. This will make catching an
    # empty string easier, as well as ensuring we can accurately detect strings
    # with certain ending characters (question marks, I'm looking at you).
    string = string.strip()
    
    # If you don't say anything to Bob...
    if string == '':
        # We have already stripped out any whitespace around the string.
        # If the string is now empty, we haven't said anything to Bob.
        return 'Fine. Be that way!'

    # If you yell at Bob...
    if string.isupper():
        # The isupper() function returns true if there is at least one alpha
        # character, and all alpha characters in the string are uppercase.
        return 'Whoa, chill out!'
    
    # If you ask Bob a question...
    if string.endswith('?'):
        # Questions end with question marks, right? A yelled question counts as
        # yelling though, and yelling was taken care of above.
        return 'Sure.'
    
    # If you say anything else to Bob...
    return 'Whatever.'
