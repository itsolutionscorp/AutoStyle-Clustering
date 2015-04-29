def combine_anagrams(words)
hash = Hash.new([])
words.each {|n| hash[n.downcase.chars.sort.join] = hash[n.downcase.chars.sort.join] + [n] }
hash.values
end
