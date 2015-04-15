def hey(comment):
    if not comment.strip():
        return 'Fine. Be that way!'
    elif comment.isupper():
        return 'Whoa, chill out!'
    elif comment.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
