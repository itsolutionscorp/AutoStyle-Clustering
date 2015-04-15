def combine_anagrams(words)
  anagrams = {}
  words.each() do |word|
    anagram = word.downcase.chars.sort.join
    if anagrams[anagram]
      anagrams[anagram] = anagrams[anagram] << word
    else
      anagrams[anagram] = [word]
    end
  end
  return anagrams.values
end


