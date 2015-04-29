def combine_anagrams(words)
  anagrams = Hash.new
  
  words.flatten.each {
    |word|
    index = word.downcase.scan(/\w/).sort.to_s
    if(anagrams.has_key? index)
      anagrams[index] << word
    else
      anagrams[index] = Array.new << word
    end
  }
  
  anagrams.values
end