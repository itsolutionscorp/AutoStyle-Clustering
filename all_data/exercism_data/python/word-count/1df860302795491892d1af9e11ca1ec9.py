import re
import collections
import functools

words_re = re.compile(r"\w+")

def words(s):
    return words_re.findall(s)

def methodonce(prop):
    def decorator(f):
        @functools.wraps(f)
        def wrapper(self, *args, **kwds):
            value = getattr(self, prop)
            if value is None:
                value = f(self, *args, **kwds)
                setattr(self, prop, value)

            return value
        return wrapper
    return decorator

class Phrase(object):
    def __init__(self, phrase):
        self._phrase = phrase
        self._word_count = None

    @methodonce("_word_count")
    def word_count(self):
        if self._word_count is None:
            self._word_count = collections.Counter(words(self._phrase.lower()))

        return self._word_count
