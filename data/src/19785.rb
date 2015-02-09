def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    sorted_word = word.downcase.chars.sort.join
    if anagrams.has_key?(sorted_word) then
      (anagrams[sorted_word] << word)
    else
      anagrams[sorted_word] = [word]
    end
  end
  results = []
  anagrams.each { |k, anagram| (results << anagram) }
  return results
end