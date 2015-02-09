def combine_anagrams(words)
    sorted_words=words.map {|x| x.downcase.chars.sort.join}.uniq
    h={}    
    sorted_words.each {|x| h[x]=[]}
    words.each {|x| h[x.downcase.chars.sort.join]=h[x.downcase.chars.sort.join]+[x]}
    return h.values    
end
