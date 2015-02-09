def combine_anagrams(words)
  anagrams = words.group_by do |word|
    word.downcase.chars.sort
  end
  anagrams = anagrams.values.map do |word_group|
    word_group.sort
  end.sort
  anagrams
end