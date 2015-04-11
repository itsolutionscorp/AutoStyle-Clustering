import unicodedata

RESPONSES = {'question': 'Sure.',
             'yell': 'Whoa, chill out!',
             'nothing': 'Fine. Be that way!',
             'other': 'Whatever.'}


def remove_unicode_category(statement, category):
    return filter(lambda c: unicodedata.category(c) != category, statement)


def remove_control_characters(statement):
    return remove_unicode_category(statement, 'Cc')


def remove_spaces(statement):
    return remove_unicode_category(statement, 'Zs')


def hey(statement):
    statement = unicode(statement)
    statement = remove_control_characters(statement)
    statement = remove_spaces(statement)

    if not statement:
        return RESPONSES['nothing']
    elif statement.isupper():
        return RESPONSES['yell']
    elif statement.endswith('?'):
        return RESPONSES['question']
    else:
        return RESPONSES['other']
