def combine_anagrams(words)
  words.group_by { |w| w.downcase.split(//).sort.to_s }.values
end

