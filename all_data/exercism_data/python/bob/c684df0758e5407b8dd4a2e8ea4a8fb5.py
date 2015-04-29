"""
Generate a response function for the following scenario.

Bob is a lackadaisical teenager.
In conversation, his responses are very limited

Bob answers 'Sure.' if you ask him a question.
He answers 'Whoa, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without
    actually saying anything.
He answers 'Whatever.' to anything else.
"""

import re


def hey(what):
    """Auto-responder for Bob"""
    responses = {
        'default': 'Whatever.',
        'question': 'Sure.',
        'yell': 'Whoa, chill out!',
        'silence': 'Fine. Be that way!'
    }
    prompt = 'default'

    if re.search('^\s*$', what):
        prompt = 'silence'

    if what.endswith('?'):
        prompt = 'question'

    # Should be able to combine the yell conditions
    # but haven't gotten them to group correctly
    if what.isupper():
        prompt = 'yell'

    if what.endswith('!') and not re.search(u'[\p{Ll}]+', what):
        prompt = 'yell'

    return responses[prompt]
