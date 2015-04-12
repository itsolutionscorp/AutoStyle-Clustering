def hey(sentence):
    """
    Bob answers 'Sure.' if you ask him a question.
    He answers 'Whoa, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him without actually saying anything
    He answers 'Whatever.' to anything else.
    """
    if not sentence.strip():
        return 'Fine. Be that way!'
    elif sentence.isupper():
        return 'Whoa, chill out!'
    elif sentence.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'