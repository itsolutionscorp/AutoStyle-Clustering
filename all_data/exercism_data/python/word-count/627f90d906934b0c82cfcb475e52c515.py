"""
Count words
"""
def word_count(word):
    """
    Word count
    """
    word_c = {}

    for wrd in word.split(' '):
        if not wrd:
            continue

        if len(wrd.split('\n')) > 1:
            for s_word in wrd.split():
                if s_word in word_c:
                    word_c[s_word] += 1
                else:
                    word_c[s_word] = 1
            continue

        if wrd in word_c:
            word_c[wrd] += 1
        else:
            word_c[wrd] = 1

    return word_c

# word_count()
