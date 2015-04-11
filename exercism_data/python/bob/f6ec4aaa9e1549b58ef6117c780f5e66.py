from collections import namedtuple

Answer = namedtuple('Answer', ['check', 'answer'])

def is_shouting(what):
    return what.isupper()

def is_asking_question(what):
    what = what.strip()
    return what and what[-1] == "?"

def is_silence(what):
    return not what.strip()

def is_anything_else(what):
    return True

def hey(what):
    answers = (
        Answer(is_shouting, "Whoa, chill out!"),
        Answer(is_asking_question, "Sure."),
        Answer(is_silence, "Fine. Be that way!"),
        Answer(is_anything_else, "Whatever.")
    )
    for answer in answers:
        if answer.check(what):
            break
    return answer.answer
