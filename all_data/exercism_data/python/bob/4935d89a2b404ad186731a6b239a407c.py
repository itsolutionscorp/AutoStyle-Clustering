def hey(statement):
    """
    Take in statements and respond to them.
    """
    statement = statement.strip()

    # Don't even try if it's empty.
    if not statement:
        return 'Fine. Be that way!'

    # If the statement is all caps, you're yelling.
    if statement.isupper():
        return 'Woah, chill out!'

    # If we end with a question mark, and we aren't
    #   yelling, a question seems reasonable.
    if statement.endswith('?'):
        return 'Sure.'

        # If nothing else is true, bail.
    return 'Whatever.'
