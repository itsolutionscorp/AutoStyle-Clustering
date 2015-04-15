def combine_anagrams(words)
  result = []
  sorted_words = {} 
  words.map do |word| 
    key = word.downcase.chars.sort.join
    if sorted_words.has_key?(key)
      sorted_words[key] << word
    else
      sorted_words[key] = [word]
    end
  end
  sorted_words.values
    
end

