def combine_anagrams(words)
  words_anagrams_group = {}
  words.each do |word| 
    key = word.downcase.chars.sort.join
    if words_anagrams_group.has_key?(key)
      words_anagrams_group[key] = words_anagrams_group[key] << word
    else
      words_anagrams_group[key] = [word]
    end
   
  end
  return words_anagrams_group.values

end

def is_anagrams(word1, word2)

  return word1.downcase.chars.sort.join.eql? word2.downcase.chars.sort.join

end


