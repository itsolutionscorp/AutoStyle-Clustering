# -*- coding: utf-8 -*-
import re
from collections import Counter


class Phrase(object):
    def __init__(self, raw_phrase_str):
        self._raw_phrase_str = raw_phrase_str

    def word_count(self):
        """Counts word occurences in given phrase and returns word counter."""
        return Counter(self._normalize(self._raw_phrase_str))

    def _normalize(self, raw_phrase_str):
        return re.findall('\w+', raw_phrase_str.lower())
