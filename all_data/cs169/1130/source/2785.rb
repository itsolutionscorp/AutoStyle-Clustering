def combine_anagrams(words)
  group = {}
  group.default = []
  words.each do |w|
    group[w.downcase.each_char.sort] += [w]
  end
  group.values
end
