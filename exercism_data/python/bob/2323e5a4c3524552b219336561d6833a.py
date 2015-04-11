from string import ascii_uppercase, printable, whitespace
"""
Bob is a lackadaisical teenager. In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""

def hey(phrase):
    """ Talk to bob.

    response = bob.hey(phrase)
    """

    # Bob has spent years building up his knowledge base. He knows
    # four whole things.
    knowledge = (nostatement, anger, question, easteregg)

    # Loop through all of his thoughts. Bob isn't much of a multi-tasker
    for thought in knowledge:
        response = thought(phrase)

        # if the thought registered, spit it out! Don't think to hard!
        if response:
            return response

    # If he didn't understand what you said, he gives up and returns
    # to being dumb.
    return 'Whatever.'

def nostatement(phrase):
    """ Was your statement full of nothing? Useless."""

    is_printable = lambda x: x in printable
    is_whitespace = lambda x: x in whitespace
    if not any([is_printable(x) and not is_whitespace(x) for x in phrase]):
        return 'Fine. Be that way!'

def anger(phrase):
    """ Bob gets angry if you yell at him """

    is_uppercase = lambda x: x in ascii_uppercase
    if phrase.upper() == phrase and any([is_uppercase(x) for x in phrase]):
        return 'Whoa, chill out!'

def question(phrase):
    """ Bob understands questions. Barely. """

    if phrase[-1] == '?':
        return 'Sure.'

def easteregg(phrase):
    """ Bob knows a little physics. """

    if phrase == 'E = m c^2':
        return """
General relativity, or the general theory of relativity, 
is the geometric theory of gravitation published by Albert Einsteinin 1916[1] 
and the current description of gravitation in modern physics. 
General relativity generalizes special relativity and
Newton's law of universal gravitation, providing a unified description 
of gravity as a geometric property of space and time, or spacetime. 
In particular, the curvature of spacetime is directly related 
to the energy and momentum of whatever matterand radiation are present. 
The relation is specified by the Einstein field equations, 
a system of partial differential equations.
"""


    
