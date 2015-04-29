values = {
    '1' : ('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'),
    '2' : ('D', 'G'),
    '3' : ('B', 'C', 'M', 'P'),
    '4' : ('F', 'H', 'V', 'W', 'Y'),
    '5' : ('K'),
    '8' : ('J', 'X'),
    '10': ('Q', 'Z')
    }


def score(word):
    
    letter_scores = []
    if word.isalpha():
        for x in word:
            for k, v in values.iteritems():
                for i in v:
                    if x.upper() == i:
                        letter_scores.append(k)
                    else:
                        pass
        letter_scores = [int(d) for d in letter_scores]
        return reduce(lambda x, y: x+y, letter_scores)
    else:
        return 0
