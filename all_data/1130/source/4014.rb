def combine_anagrams(words)
  # grouping words by their sorted characters will group by anagrams
  # return an array
  words.group_by { |word| word.downcase.each_char.sort.join }.values
end
