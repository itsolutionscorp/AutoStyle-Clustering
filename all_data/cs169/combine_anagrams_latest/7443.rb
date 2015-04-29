def combine_anagrams(words)
  anagrams = {}

  words.each_index { |i| 
    anagram = words[i].downcase.chars.sort.join
    if anagrams.has_key? anagram 
      anagrams[anagram] << words[i]
    else
      anagrams[anagram] = []<<words[i]
    end

  }

  anagrams.values
end

