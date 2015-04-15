import collections


def is_yelling(statement):
    return statement.isupper()


def is_question(statement):
    return statement and statement[-1] == '?'


def is_silence(statement):
    return not statement.strip()


def is_anything(statement):
    return True

Rule = collections.namedtuple('Rule', ['test', 'response'])

RULES = [
    Rule(is_yelling, 'Woah, chill out!'),
    Rule(is_question, 'Sure.'),
    Rule(is_silence, 'Fine. Be that way!'),
    Rule(is_anything, 'Whatever.'),
]


def hey(statement, rules=RULES):
    for rule in rules:
        if rule.test(statement):
            return rule.response
