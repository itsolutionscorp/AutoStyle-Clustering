def detect_anagrams(word, candidates):

    correct = []
    letters = list(word.lower())
    letters.sort()
    a = ''.join(letters)

    for x in candidates:
        if x.lower() != word.lower():
            letters2 = list(x.lower())
            letters2.sort()
            b = ''.join(letters2)
            if b == a:
                correct.append(x)
            else:
                pass
        else:
            pass
            
    return correct
