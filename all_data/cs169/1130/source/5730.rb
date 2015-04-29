#Part 3: Anagrams

def combine_anagrams(words)
    anagram_dict = Hash.new   

    words.each{|word| 
        
        anagramHash = word.downcase.chars.sort.join
        
        anagram_dict.has_key?(anagramHash) ?        
            (anagram_dict.store(anagramHash, anagram_dict[anagramHash].concat([word]))) :
            (anagram_dict.store(anagramHash, [word]))
    }
    
    return anagram_dict.values
    
end 

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
