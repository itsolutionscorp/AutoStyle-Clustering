'amyfu\'s solution to exercism bob'

def hey(phrase):
    """function for talking to bob"""

    # if you say nothing to bob
    if len(phrase) == 0 or phrase.isspace():
        return 'Fine. Be that way!'
    else:
        # if you yell at bob by typing in all caps
        if phrase.isupper():
            return 'Whoa, chill out!'

        # if you ask bob a question
        if phrase[-1] == '?':
            return 'Sure.'

        # if you say anything else to bob
        return 'Whatever.'
