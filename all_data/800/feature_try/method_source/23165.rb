def combine_anagrams(words)
  temp = words
  p = Array.new
  h = Hash.new
  temp.each { |word| h = makeHash(h, word) }
  h.values.each { |ary| p.push(ary) }
  return p
end