# -*- coding: utf-8 -*-
import re
from collections import defaultdict


class Phrase(object):
    def __init__(self, raw_phrase_str):
        self._raw_phrase_str = raw_phrase_str

    def word_count(self):
        phrase = self._normalize(self._raw_phrase_str)

        word_count_dict = defaultdict(int)

        for word in phrase.split():
            word_count_dict[word] += 1

        return dict(word_count_dict)

    def _normalize(self, raw_phrase_str):
        return re.sub('[!&@$%^&:,]', '', raw_phrase_str.lower())
