def combine_anagrams(words)
  anagrams = Hash.new
  words.each { |word|
    anagram = word.downcase.chars.sort.join
    if (anagrams.has_key?(anagram))
      anagrams[anagram] << word
    else
      anagrams[anagram] = [word]
    end
  }
  anagrams.values
end
