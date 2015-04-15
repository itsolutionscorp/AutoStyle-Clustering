require 'rubygems'

def combine_anagrams(words)
  #word => anagram
  #{ anagram => [word1, word2] }

  anagrams = {}

  words.each do |word|
    anagram = word.downcase.scan(/./).sort.join
    if anagrams[anagram] == nil
      words_a = Array.new << word
      anagrams[anagram] = words_a
    else
      anagrams[anagram] << word
    end
  end

  anagrams_a = Array.new
  anagrams_a2 = Array.new
  anagrams.each do |anagram, words_a|
    anagrams_a << words_a
  end

  return anagrams_a

end