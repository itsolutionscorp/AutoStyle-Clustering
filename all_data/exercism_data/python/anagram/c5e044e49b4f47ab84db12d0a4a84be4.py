__author__ = 'FlavioMiranda'
def detect_anagrams(word,list):
    anagrams = []
    if not list: return anagrams

    dic = buildAnagram(word)
    for strCmp in list:
        ## Same word cannot be anagram
        if strCmp.lower()==word.lower(): continue

        dic2 =  buildAnagram(strCmp)
        if dic.keys()==dic2.keys():
            anagrams.append(strCmp)
            for i in dic.keys():
                if dic[i]!=dic2[i]:
                    anagrams.pop(len(anagrams)-1)
                    break

    return anagrams

def buildAnagram(word):
    # word to dic
    # should be easier to understand
    dic = {}
    for i in range(0,len(word)):
        dic[word[i].lower()] = dic.get(word[i].lower(),0)+1
    return dic
