def combine_anagrams(words)
  words.group_by {|g| g.downcase.split(//).sort.join}.values
end
