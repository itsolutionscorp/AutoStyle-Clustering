# -*- coding: UTF-8 -*-


def check_is_yelling(q):
    # check if any text characters in question
    if any(c.isalpha() for c in q):
        for c in q:
            # check if any character is not uppercase
            if c.isalpha() and c.upper() != c:
                # found lower case letter, return false
                return False
        # found no lower case letters, return true
        return True
    # found no text characters, return false
    return False


def check_is_nothing(q):
    # check if question contains just whitespace.
    return q.isspace()


def check_is_question(q):
    # check if question ends in a question mark.
    return q[-1:] == '?'


def hey(question):
    # check if question is a string and contains something
    if isinstance(question, basestring) and len(question) >= 1:
        if check_is_yelling(question):
            return "Whoa, chill out!"
        if check_is_question(question):
            return "Sure."
        if check_is_nothing(question):
            return "Fine. Be that way!"
        return "Whatever."
    return "Fine. Be that way!"
