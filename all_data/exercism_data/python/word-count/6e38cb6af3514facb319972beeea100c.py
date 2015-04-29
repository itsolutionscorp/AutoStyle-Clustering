import re


def word_count(phrase):
    words = re.split('[\s|.,!?;:]', phrase)
    words = [word.lower() for word in words if len(word) > 0 and re.match('[a-zA-Z0-9]', word)]
    glossary = {}
    for word in set(words):
        glossary[word] = 0
    for word in words:
        glossary[word] += 1
    return glossary
