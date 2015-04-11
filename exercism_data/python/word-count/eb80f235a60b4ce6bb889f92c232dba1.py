# -*- coding: utf-8 -*-
import re
from collections import Counter


class Phrase(object):
    def __init__(self, raw_phrase_str):
        self._raw_phrase_str = raw_phrase_str

    def word_count(self):
        word_list = self._normalize(self._raw_phrase_str)

        word_count_dict = Counter(word_list)

        return dict(word_count_dict)

    def _normalize(self, raw_phrase_str):
        return filter(None, re.split('\W+', raw_phrase_str.lower()))
