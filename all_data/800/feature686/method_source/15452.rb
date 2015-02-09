def combine_anagrams(words)
  groups = {}
  words.each do |word|
    base = word.downcase.split(//).sort.join
    groups[base] = [] if (not groups[base])
    (groups[base] << word)
  end
  return groups.each_value.to_a
end