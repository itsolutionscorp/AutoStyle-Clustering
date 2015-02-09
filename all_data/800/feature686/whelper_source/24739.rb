def combine_anagrams(words)
  anagram_words = words.map { |w| w.downcase.chars.sort.join }
  sorts = anagram_words.uniq
  groups = Array.new(sorts.length) { Array.new }
  words.each_index { |i| (groups[sorts.index(anagram_words[i])] << words[i]) }
  return groups
end

