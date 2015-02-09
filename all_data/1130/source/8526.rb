def combine_anagrams(words)
  anagrams = Hash.new()
  
  for word in words do
    norm_word = word.downcase.chars.sort.join
    if anagrams.has_key?(norm_word)
      anagrams[norm_word] << word
    else
      anagrams[norm_word] = [word]
    end    
  end
  
  return anagrams.values
end