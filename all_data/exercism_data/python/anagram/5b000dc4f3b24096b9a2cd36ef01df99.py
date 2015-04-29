#!/usr/bin/env python3

class Anagram(str):
    def is_anagram(self, other):
        if self == other:
            return False
        return sorted(self.lower()) == sorted(other.lower())

    def match(self, other):
        return list(filter(self.is_anagram, other))
