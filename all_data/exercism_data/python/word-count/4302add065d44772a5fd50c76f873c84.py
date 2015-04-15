class Phrase:
    def __init__(self,a_phrase):
        a_phrase = ''.join([c for c in a_phrase if c not in ('!', '?','&','@','$','%','^',':',',')])
        self.phrase = a_phrase

    def words(self):
        word_lst=self.phrase.split(' ')
        word_lst=(x for x in word_lst if x not in (' '))
        return word_lst

    def word_count(self):
        word_index={}
        word_lst = self.words()
        for s in word_lst:
            s = s.lower()
            if s in word_index.keys():
                word_index[s] = word_index[s]+1
            else:
                word_index[s] = 1
        return word_index
