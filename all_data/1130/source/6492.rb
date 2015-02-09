def combine_anagrams(words)
  words.group_by { |word| word.chars.sort {|a,b| a.casecmp(b)}}.values
end