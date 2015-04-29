def combine_anagrams(words)
h = Hash.new([]);
words.each do |word|
aWord = word.downcase.chars.sort.join;
h[aWord] += [word]
end
return h.values;
end