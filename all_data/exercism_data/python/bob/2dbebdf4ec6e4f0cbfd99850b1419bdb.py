"""Bob, the modular teenager.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""

from __future__ import unicode_literals

# Separated for future localization
STRINGS = {
    'yell': 'Whoa, chill out!',
    'question': 'Sure.',
    'default': 'Whatever.',
    'address': 'Fine. Be that way!',
}


def hey(what):
    """hey(what) accepts a string (what) and returns Bob's response to it.

    Bob recognizes four different types of strings in priority order:
    1) Any string (default)
    2) An empty string (address)
    3) A string ending in a '?' mark (question)
    4) A string with only capital letters and at least one letter (yell)

    In practical terms, this means yelling beats questioning and non-default
    beats default."""

    # Ignore non-significant whitespace.
    what = what.strip()

    if not what:  # If what is just empty or whitespace
        # We can stop here as no other definition matches the empty string.
        return STRINGS['address']

    if what.isupper():  # If what is unambiguously uppercase
        return STRINGS['yell']

    if what[-1] == '?':
        # A string can only be a question if it ends in '?'
        return STRINGS['question']

    return STRINGS['default']
