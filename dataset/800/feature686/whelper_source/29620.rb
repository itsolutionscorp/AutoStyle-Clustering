def combine_anagrams(words)
  h = Hash.new { |h, k| h[k] = [] }
  words.each { |w| (h[w.downcase.chars.sort.join] << w) }
  return h.values
end

