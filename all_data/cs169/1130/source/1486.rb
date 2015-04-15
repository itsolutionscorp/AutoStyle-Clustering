def combine_anagrams(words)
  return words.group_by {|f| f.downcase.chars.sort { |a, b| a.casecmp(b) } .join}.values
end