def combine_anagrams(words)
  words.group_by do |string|
        string.downcase.split(//).sort
  end.values
end