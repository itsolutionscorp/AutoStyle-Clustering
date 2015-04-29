def combine_anagrams(words)
  words.group_by { |word| word.downcase.scan(/./).sort.to_s }.values
end