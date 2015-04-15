# Bob chatbot module.


def hey(what):
    """A basic teenage chatbot named Bob.

    If the user says nothing or whitespace, return 'Fine. Be that way!'.
    If the user is yelling (All caps), return 'Whoa, chill out!'.
    If the user asks a question, return 'Sure.'.
    If the user says anything else, return 'Whatever.'.
    """
    stripped_what = what.strip()
    # if the user says nothing
    if not stripped_what:
       return 'Fine. Be that way!'

    # if the user is yelling
    if stripped_what.isupper():
        return 'Whoa, chill out!'

    # if the user asks a question
    if stripped_what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
