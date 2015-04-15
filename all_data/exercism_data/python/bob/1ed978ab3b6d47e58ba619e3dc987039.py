# -*- coding: utf-8 -*-

import re

silence = re.compile(r"^[\s]+$")

def hey(statement):
    if not isinstance(statement, basestring):
	    print "That's not a sentence."
    else:
        if (statement.endswith('?')) and not statement.isupper():
            return "Sure."
        elif statement.isupper():
            return "Whoa, chill out!"
        elif (silence.match(statement) is not None) or (statement == ""):
		    return "Fine. Be that way!"
        else:
            return "Whatever."
