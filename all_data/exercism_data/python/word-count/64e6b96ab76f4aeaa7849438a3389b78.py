def word_count(phrase):
    word_list = phrase.split()
    counts = [word_list.count(word) for word in set(word_list)]
    return dict(zip(list(set(word_list)),counts))
        
