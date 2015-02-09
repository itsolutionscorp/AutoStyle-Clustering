def combine_anagrams(words)
  anagram = Hash.new { |h, k| h[k] = Array.new }
  words.each { |word| (anagram[word.downcase.chars.sort.join] << word) }
  return anagram.values
end