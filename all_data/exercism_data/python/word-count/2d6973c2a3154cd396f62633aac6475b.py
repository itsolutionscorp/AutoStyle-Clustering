__author__ = 'grdunn'
import re
import string


def word_count(phrase):
    """

    :param phrase:
    :rtype : dict

    """

    words = {}
    allow = string.letters + string.digits

    raw = [re.sub('[^%s]' % allow, '', i).lower() for i in phrase.split() if re.match('[A-Za-z0-9]', i)]

    for word in raw:
        if not word in words:
            words[word] = 1
        else:
            words[word] += 1

    return words
