def combine_anagrams(words)
  h = Hash.new { |k, v| k[v] = [] }
  words.each { |w| (h[w.downcase.chars.sort.join] << w) }
  return h.values
end