def detect_anagrams( bw, word_list ):
    return [ w for w in word_list
             if sorted( bw.lower() ) == sorted( w.lower() )
             and bw.lower() != w.lower() ]
