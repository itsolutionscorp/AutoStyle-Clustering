def combine_anagrams(words)
  words.group_by { |elt| elt.downcase.chars.sort }.values
end

