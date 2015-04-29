def combine_anagrams(words)
  anagram = Hash.new
  anagram_words = Array.new
  for word in words
    anagram_key = word.downcase.split(//).sort.join
    if anagram[anagram_key].class == Array then
      anagram_words = anagram[anagram_key] + [word]
    else
      anagram_words = [word]
    end
    anagram[anagram_key]=anagram_words
  end
  return anagram.values
end

