def combine_anagrams(words)
    anagram_hash = {}
    words.each do |word|
        canonical = word.downcase.chars.sort.join
        #print word, " -> ", canonical , "\n"
        grp = anagram_hash[canonical]
        if !grp then grp = [] end
        grp << word   
        anagram_hash[canonical] = grp
        #print "-->", anagram_hash[canonical], "\n"
    end

    #print "done filling anagram hash\n"
     
    #p anagram_hash
    
    anagram_arr = []
    anagram_hash.each_value do |anagram_group|
        anagram_arr << anagram_group
    end

    return anagram_arr
end
p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'fOur','SCar', 'creams', 'scrEAM'])
