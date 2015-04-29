#! /usr/bin/env python


class Anagram(object):
    def __init__(self, word):
        self.txt = word.lower()
        self.llist = sorted(self.txt)

    def match(self, wlist):
        """
        [w for w in wlist if self.txt != w.lower() and \
                             not cmp(self.llist, sorted(w.lower()))]
        """
        rc = []
        for w in wlist:
            if self.txt != w.lower():
                if not cmp(self.llist, sorted(w.lower())):
                    rc.append(w)
        return rc
