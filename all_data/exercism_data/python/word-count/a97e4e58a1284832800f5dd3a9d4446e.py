class Phrase:
    def __init__(self,a_word):
        a_word = ''.join([c for c in a_word if c not in ('!', '?','&','@','$','%','^',':',',')])
        self.word = a_word

    def word_count(self):
        dic={}
        arr=self.word.split(' ')
        arr=(x for x in arr if x not in (' '))
        for s in arr:
            s = s.lower()
            if s in dic.keys():
                dic[s] = dic[s]+1
            else:
                dic[s] = 1
        return dic
