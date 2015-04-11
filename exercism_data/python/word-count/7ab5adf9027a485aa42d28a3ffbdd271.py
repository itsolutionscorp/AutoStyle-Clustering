#!/usr/bin/env python
# -*- coding: utf-8 -*-
from collections import Counter

def word_count(sentence):
    counter = Counter(sentence.split())
    return counter
