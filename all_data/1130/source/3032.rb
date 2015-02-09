def combine_anagrams(words)
    anagram_hash={}
    words.each do |w|        
        sorted=w.downcase.chars.sort.join        
        if anagram_hash.has_key? sorted
            anagram_hash[sorted]<<w
        else
            anagram_hash[sorted]=[w]
        end
    end
    anagram_hash.values
end


#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
