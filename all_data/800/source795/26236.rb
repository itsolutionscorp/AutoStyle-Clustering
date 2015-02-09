def combine_anagrams(words)
  words.group_by{|w| w.downcase.chars.sort.to_s}.values
end
