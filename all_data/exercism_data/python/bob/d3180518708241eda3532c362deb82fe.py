def hey(statement):
    # Let's wrap this in a try block in case any of these methods fails because we got an unexpected object
    try:
        # Test for saying nothing. Statement might be nothing, or might just have whitespaces in it.
        if len(statement) is 0 or statement.isspace():
            return u"Fine. Be that way!"
        # Test for yelling. If everything is uppercase, we're being yelled at!
        elif statement.isupper():
            return u"Whoa, chill out!"
        # Test for questions. If it ends with a question mark, its a question.
        elif statement.endswith('?'):
            return u"Sure."
    except:
        # We don't really care if this fails.
        pass
    # If the above conditions were not met, or there was an exception, return the default response
    return u"Whatever."
