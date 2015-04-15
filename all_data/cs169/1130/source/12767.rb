def combine_anagrams(words)
  anagram_groups = []
  answer_array = []
  words.each do |word|
    anagram_group = word.downcase.each_char.sort.join
    if not anagram_groups.include?anagram_group
      anagram_groups << anagram_group
      answer_array << [word]
    else
      anagram_index = anagram_groups.index(anagram_group)
      answer_array[anagram_index] << word
    end
  end
  return answer_array
end
