from collections import Counter
class Phrase:
    def __init__(self,str):
        str = ''.join([c for c in str if c not in ('!', '?','&','@','$','%','^',':',',')])
        print(str)
        self.str = str

    def word_count(self):
        dic={}
        arr=self.str.split(' ')
        arr=(x for x in arr if x not in (' '))
        for s in arr:
            s = s.lower()
            if s in dic.keys():
                dic[s] = dic[s]+1
            else:
                dic[s] = 1
        return dic
