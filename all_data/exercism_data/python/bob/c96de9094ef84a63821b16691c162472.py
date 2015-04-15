DEFAULT_ANSWER = 'Whatever.'
YELLING_ANSWER = 'Whoa, chill out!'
QUESTION_ANSWER = 'Sure.'
SILENT_ANSWER = 'Fine. Be that way!'

def hey(what):
    what = what.rstrip()
    if is_silent(what):
        return SILENT_ANSWER
    if is_yelling(what):
        return YELLING_ANSWER
    if is_question(what):
        return QUESTION_ANSWER
    return DEFAULT_ANSWER

def is_silent(what):
    return len(what) == 0

def is_yelling(what):
    return what.isupper()

def is_question(what):
    return what[-1] == '?'
