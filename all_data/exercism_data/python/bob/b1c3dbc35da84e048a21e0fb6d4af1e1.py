#!/usr/bin/env python
import unicodedata

def remove_accents(what):
    return ''.join([ x for x in \
                unicodedata.normalize('NFD', unicode(what)) \
                if unicodedata.category(x) != 'Mn'])

class Bob:
    def __init__(self, default="Whatever."):
        self.rules = list()
        self.default = default

    def learn(self, rule):
        self.rules.append(rule)

    def preprocess(self, what):
        return remove_accents(what).strip()

    def hey(self, what):
        what = self.preprocess(what)
        for x in self.rules:
            response = x(what)
            if response is not None: return response
        return self.default

    @staticmethod
    def teach_bob(bob):
        def all_upper(what):
            letters = [x.isupper() for x in what if x.isalpha()]
            return letters and all(letters)

        bob.learn(lambda what: 'Fine. Be that way!' if not what.strip() else None)
        bob.learn(lambda what: 'Whatever.' if what.startswith('Let\'s') else None)
        bob.learn(lambda what: 'Whoa, chill out!' if ' ' in what and what.endswith('!') else None)
        # consider sentence with all char in upper cases as shouting
        bob.learn(lambda what: 'Whoa, chill out!' if all_upper(what) else None)
        bob.learn(lambda what: 'Sure.' if what.endswith('?') else None)
        bob.learn(lambda what: 'Whatever.' if not [x for x in what if x.isalpha()] else None)

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    bob = Bob()
    Bob.teach_bob(bob)

    return bob.hey(what)
