def combine_anagrams(words)

words.map {|n| n.downcase.chars.sort.join}.uniq.collect { |w| words.select {|x| x.downcase.chars.sort.join == w}}



end
