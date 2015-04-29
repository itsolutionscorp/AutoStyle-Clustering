def isquestion(what):
    return what.endswith("?") and not isshouting(what)

def isshouting(what):
    return what.isupper()

def issilence(what):
    return what == "" or what.isspace()

def hey(what):
    """Bob is a lackadaisical teenager. In conversation, his responses are very limited."""
    # Bob answers 'Sure.' if you ask him a question.
    if isquestion(what):
        return "Sure."
    # He answers 'Whoa, chill out!' if you yell at him.
    if isshouting(what):
        return "Whoa, chill out!"
    # He says 'Fine. Be that way!' if you address him without actually saying anything.
    if issilence(what):
        return "Fine. Be that way!"
    # He answers 'Whatever.' to anything else.
    return "Whatever."
