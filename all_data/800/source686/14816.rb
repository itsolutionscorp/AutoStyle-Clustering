# HW 1 - Part 3 - a - Anagrams
def combine_anagrams(words)
  word_hash = Hash.new []
  anagrams = Array.new
  
  #Determine anagrams
  words.each do | word |
    word_hash[word.downcase.chars.sort] += [word]
  end
  
  #Store output in an array of arrays containing anagrams  
  word_hash.each do | key, val |
    anagrams.push word_hash[key]
  end
  anagrams
end

 