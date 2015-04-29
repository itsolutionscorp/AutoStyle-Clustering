class Phrase(object):
    def __init__(self, text_phrase):
        self.text_phrase = text_phrase

    def word_count(self):
        count_obj = {}

        for word in self.text_phrase.split(' '):
            word = ''.join(s for s in word if s.isalnum()).lower()
            if word:
                if word in count_obj:
                    count_obj[word] += 1
                else:
                    count_obj[word] = 1

        return count_obj
