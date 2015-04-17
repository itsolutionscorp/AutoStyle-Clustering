def detect_anagrams( base_word, word_list ):
    base_word = base_word.lower()
    anagrams = []
    for word in word_list:
        if( sorted( base_word ) == sorted( word.lower() )
            and base_word != word.lower() ):     
            anagrams.append( word )
    return anagrams
