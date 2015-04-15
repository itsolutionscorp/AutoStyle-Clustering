#!/usr/bin/env python

import re

class Bob(object):
    
    def hey(self, text):
        text = text.strip()

        if not text:
            return "Fine. Be that way!"

        match = re.search('[a-zA-Z\d]', text)
        if not match:
            return "Whatever."
        elif re.search('[a-zA-Z]', text) and text.upper() == text:
            return "Woah, chill out!"
        elif text[-1] == "?":
            return "Sure."
                    
        return "Whatever."
