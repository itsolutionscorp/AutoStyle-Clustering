def combine_anagrams(words)
  words.group_by { |word| word.downcase.split(//).sort.to_s }.values
end

