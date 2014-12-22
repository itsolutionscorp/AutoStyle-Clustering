def combine_anagrams(words)
  anagram_groups = words.anagram_groups
  result = []
  anagram_groups.each { |i| (result << []) }
  words.each do |word|
    matching_index = anagram_groups.index(word.sort_characters)
    result[matching_index].push(word)
  end
  result
end