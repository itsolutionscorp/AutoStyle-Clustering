import re

class Phrase:
    def __init__(self, text):
        self.text = text

    def word_count(self):
        count = {}

        #for word in [w.lower() for w in re.findall(r'\w+', self.text)]:
        #    if count.get(word):
        #        count[word] += 1
        #    else:
        #        count[word] = 1

        words = re.findall(r'\w+', self.text)
        for word in [w.lower() for w in words]:
            if count.get(word):
                count[word] += 1
            else:
                count[word] = 1

        #words = re.findall(r'\w+', self.text)
        #for word in words:
        #    lword = word.lower()
        #    if count.get(lword):
        #        count[lword] += 1
        #    else:
        #        count[lword] = 1

        return count
