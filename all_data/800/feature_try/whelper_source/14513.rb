def combine_anagrams(words)
  anagrams = words.group_by { |w| w.upcase.split(/\s*/).sort.join }.values
end

