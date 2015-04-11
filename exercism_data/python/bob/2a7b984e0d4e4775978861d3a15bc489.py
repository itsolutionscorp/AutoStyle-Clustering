#!/usr/bin/env python3
import operator

# Say this when no statement is communicated
RESPONSE_BLANK = "Fine. Be that way!"

# Say this when asked a question
RESPONSE_QUESTION = "Sure."

# Say this when yelled at (caps lock)
RESPONSE_EXCLAMATION = "Whoa, chill out!"

# Say this to every other statement
RESPONSE_DEFAULT = "Whatever."

# Rules that govern each response.  The first entry is the response and the
# second is a callable that returns true if we should return this response.
# Order matters.
RESPONSES = (
    (RESPONSE_BLANK, operator.not_),
    (RESPONSE_EXCLAMATION, operator.methodcaller('isupper')),
    (RESPONSE_QUESTION, operator.methodcaller('endswith', '?')),
)

def hey(what):
    what = what.strip()
    for response, test in RESPONSES:
        if test(what):
            return response
    return RESPONSE_DEFAULT
