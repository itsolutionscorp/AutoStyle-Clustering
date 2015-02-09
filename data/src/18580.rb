def combine_anagrams(words)
  words.group_by { |w| w.downcase.split("").sort!.join }.values
end