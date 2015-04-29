def combine_anagrams(words)
  anagrams = {}
  words.each { |i| 
    anagram_key = i.chars.sort { |a, b| a.casecmp(b) } .join.downcase
    if anagrams.has_key? anagram_key 
      anagrams[anagram_key].push (i)
    else
      anagrams[anagram_key] = [i]
    end
    }
  anagrams.values
end