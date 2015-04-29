def combine_anagrams(words)
  words.group_by {|a| a.downcase.split(//).sort}.values
end
