#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    """ Return a response from 'Bob' and his limited vocabulary

    :param what: What are you telling to Bob
    :type what: str or unicode
    :return: Bob's response
    :rtype: str
    """
    if type(what) != str and type(what) != unicode:
        #He answers 'Whatever.' to anything else.
        response = "Whatever."
    else:
        what = what.strip()
        if not what:
            #He says 'Fine. Be that way!'
            # if you address him without actually saying anything.
            response = "Fine. Be that way!"
        elif what.isupper():
            #He answers 'Whoa, chill out!' if you yell at him.
            response = "Whoa, chill out!"
        elif what.endswith('?'):
            #Bob answers 'Sure.' if you ask him a question.
            response = "Sure."
        else:
            #He answers 'Whatever.' to anything else.
            response = "Whatever."

    return response
