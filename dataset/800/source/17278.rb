def combine_anagrams(words)
a = Array.new
b = Array.new
words.each {|x| a = words.select {|y| y.downcase.chars.sort.join == x.downcase.chars.sort.join}; b.push a unless a==[];words = words - a}
return b
end