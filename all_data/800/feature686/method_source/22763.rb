def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    sorted_anagram = word.chars.sort { |a, b| a.casecmp(b) }.join
    anagrams[sorted_anagram] ||= []
    (anagrams[sorted_anagram] << word)
  end
  return anagrams.values
end