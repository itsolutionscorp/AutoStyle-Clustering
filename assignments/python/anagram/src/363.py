def detect_anagrams(targ, words):
    
    # sort the letters in the target word
    targ_sorted = ''.join(sorted(targ.lower()))
    
    # sort the letters from each word from the list
    words_sorted = map(lambda x:''.join(sorted(x.lower())),words)
    
    # return those sorted words that match the sorted target
    matching_inds = filter(lambda i:words_sorted[i] == targ_sorted,
                           range(len(words)))
    matching_words = [words[i] for i in matching_inds]
     
    # filter out words that match the target and return
    return filter(lambda x:x.lower() != targ.lower(),
                  matching_words)
