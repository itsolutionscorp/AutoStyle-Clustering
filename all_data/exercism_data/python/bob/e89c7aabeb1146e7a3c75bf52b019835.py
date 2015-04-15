import unicodedata

STANDARD_RESPONSES = {
    'question': 'Sure.',
    'exclamation': 'Woah, chill out!',
    'empty': 'Fine. Be that way!',
    'other': 'Whatever.'
}
def hey(*statements):
    for statement in statements:
        if type(statement) != str:
            try:
                statement = str(statement)
            except:
                statement = unicodedata.normalize('NFKD', statement).encode('ascii','ignore')

        if is_empty(statement):
            return STANDARD_RESPONSES['empty']

        punctuation = statement[len(statement) - 1]

        if is_exclamation(statement, punctuation):
            return STANDARD_RESPONSES['exclamation']
        elif is_question(statement, punctuation):
            return STANDARD_RESPONSES['question']
        else:
            return STANDARD_RESPONSES['other']


def is_empty(statement):
    if len(statement) == 0 or statement.isspace():
        return True
    else:
        return False


def is_question(statement, punctuation):
    if punctuation == '?':
        return True
    return False


def is_exclamation(statement, punctuation):
    if punctuation == '!':
        if statement.isupper():
            return True
        else:
            return False
    elif statement.isupper():
        return True

    return False
