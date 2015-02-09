def combine_anagrams(words)
  h = Hash.new { |hash, val| hash[val] = [] }
  words.each { |val| (h[val.downcase.chars.sort.join] << val) }
  h.values
end

