def hey(comment):
    """
    This function implements the first python exercise from exercism.io, "bob".
    """
    comment = comment.strip()
    # empty string
    if comment == '':
        return 'Fine. Be that way!'
    # comment has at least one letter and all letters are uppercase
    elif comment.isupper():
        return 'Whoa, chill out!'
    # final character is a question mark
    elif comment[-1] == '?':
        return 'Sure.'
    # all other cases
    else:
        return 'Whatever.'
