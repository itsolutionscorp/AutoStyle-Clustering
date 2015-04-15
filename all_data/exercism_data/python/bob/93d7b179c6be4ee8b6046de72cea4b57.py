def hey(sentence):
    """
    Bob answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without actually saying anything
    He answers 'Whatever.' to anything else.
    """
    if not sentence.strip():
        return 'Fine. Be that way!'
    elif isshout(sentence):
        return 'Whoa, chill out!'
    elif sentence.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'

def isshout(sentence):
    """
    Determines whether the sentence is a shout or not
    """
    chrs = filter(unicode.isalpha, sentence)
    return len(chrs) and len(chrs) == len(filter(unicode.isupper, chrs))
