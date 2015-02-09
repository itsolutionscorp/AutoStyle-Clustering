def combine_anagrams(words)
  words.group_by { |w| w.downcase.chars.sort.join}.map { |x| x[1] }
end