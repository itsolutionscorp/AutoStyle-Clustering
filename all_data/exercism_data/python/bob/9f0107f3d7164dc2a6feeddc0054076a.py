def hey(conv):
    """ Ask this teen a question, get attitude back. """

    # Check for null or whitespace-only conversation
    if not conv or conv.isspace():
        return "Fine. Be that way!"
    # Contains words with full uppercase?
    elif conv.isupper():
        return "Whoa, chill out!"
    # If it's not yelling, is it still a question?
    elif conv.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
