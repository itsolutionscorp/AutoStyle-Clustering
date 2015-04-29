#
# Cached responses to each category
#
def question_response():
    return "Sure."

def yell_response():
    return "Whoa, chill out!"

def no_response():
    return "Fine. Be that way!"

def any_response():
    return "Whatever."

#
# True/False conditional tests for each response
#
def is_question(source):
    # Note: check length incase future hey method conditional
    # does not eliminate possibility of empty string
    if len(source) and source[-1] == '?':
        return True
    return False

def is_yell(source):
    return source.isupper()

def is_no(source):
    return len(source) == 0 or source.isspace()

def is_any(source):
    if len(source):
        return True
    return False
    
#
# "main" loop
# Progression makes most sense to me:
# Check for empty, if not empty,
# check for yelling, then check for
# question, and finally give our
# 'Whatever' response for anything else.
#
def hey(what):
    if is_no(what):
        return no_response()
    else:
        if is_yell(what):
            return yell_response()
        elif is_question(what):
            return question_response()
        else:
            return any_response()
