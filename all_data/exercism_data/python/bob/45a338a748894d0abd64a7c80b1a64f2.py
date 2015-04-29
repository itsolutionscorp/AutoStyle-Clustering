# -*- coding: utf-8 -*-

from __future__ import unicode_literals

# Check input against certain rules and return the correct response


# Rules:

# 1. Bob answers 'Sure.' if you ask him a question.

# 2. He answers 'Whoa, chill out!' if you yell at him.

# 3. He says 'Fine. Be that way!' if you address him without actually saying
#    anything.

# 4. He answers 'Whatever.' to anything else.

def hey(query):
    if query.strip() is "":
        return "Fine. Be that way!"         # 3.
    elif query.isupper() is True:
        return "Whoa, chill out!"           # 2.
    elif query.endswith("?") is True:
        return "Sure."                      # 1.
    else:
        return "Whatever."                  # 4.
