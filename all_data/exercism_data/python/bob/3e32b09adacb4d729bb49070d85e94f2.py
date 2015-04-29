# bob module

import re


def hey(what):
    """
    Responds as Bob would do in conversation:

    * Answers 'Sure.' if you ask him a question.
    * Answers 'Whoa, chill out!' if you yell at him.
    * Says 'Fine. Be that way!' if you address him without actually saying
    anything.
    * Answers 'Whatever.' to anything else.
    """
    question = re.search('[?]\s*$', what, re.UNICODE)
    lowercase = re.search('[a-z]+', what, re.UNICODE)
    uppercase = re.search('[A-Z]+', what, re.UNICODE)
    numbers = re.search('[0-9]+', what, re.UNICODE)

    if (uppercase and not lowercase):
        x = 'Whoa, chill out!'
    elif question:
        x = 'Sure.'
    elif not lowercase and not numbers and not uppercase:
        x = 'Fine. Be that way!'
    else:
        x = 'Whatever.'
    return x
