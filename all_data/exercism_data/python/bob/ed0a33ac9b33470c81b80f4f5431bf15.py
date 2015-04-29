# -*- coding: utf-8 -*-
def hey(hey):
    answers = [
        u"Whatever.",
        u"Whoa, chill out!",
        u"Sure.",
        u"Fine. Be that way!"
    ]
    has_some = False    
    no_lower = True
    has_upper = False
    has_question = False

    for w in hey:
        if not w.isspace():
            has_some = True
        if w.isalpha():
            has_question = False
            if w.islower():
                no_lower = False
            else:
                has_upper = True
        if w == u'?':
            has_question = True

    if no_lower and has_upper:
        return answers[1]

    if has_question:
        return answers[2]

    if not has_some:
        return answers[3]

    return answers[0]
