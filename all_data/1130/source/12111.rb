def combine_anagrams(words)
    sorted_words=words.map {|x| x.downcase.chars.sort.join}.uniq
    h={}    
    sorted_words.each {|x| h[x]=[]}
    words.each {|w| h[w.downcase.chars.sort.join]=h[w.downcase.chars.sort.join]+[w]}
    return h.values
end