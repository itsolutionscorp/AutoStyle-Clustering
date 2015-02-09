def combine_anagrams(words)
  anagrams = Hash.new([])
  words.each { |w| anagrams[w.downcase.chars.sort.to_s] += [w] }
  anagrams.values
end

