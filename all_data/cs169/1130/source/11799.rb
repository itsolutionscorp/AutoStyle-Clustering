def combine_anagrams(words)
  words.inject({}) do |hash, word|
    found = false
    hash.keys.each do |key|
      if are_anagrams?(key, word)
        hash[key] << word
        found = true
      end
    end
    hash[word] = [word] unless found
    hash
  end.values
end

def are_anagrams?(word1, word2)
  word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 'scream']).inspect
