import re


class Phrase():

    def __init__(self, sentence):
        self.sentence = sentence

    def word_count(self):

        ret = {}
        pattern_non_word = '\W'

        mylist = self.sentence.split(' ')
        for item in mylist:

            # substitue any non-word character with empty string
            item = re.sub(pattern_non_word, '', item)
            if item.isalnum():
                ret[item.lower()] = ret.get(item.lower(), 0) + 1

        return ret
