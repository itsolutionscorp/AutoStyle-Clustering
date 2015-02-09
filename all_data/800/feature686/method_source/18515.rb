def combine_anagrams(words)
  hasher = Hash.new { |h, k| h[k] = [] }
  words.each { |w| (hasher[w.downcase.chars.sort.join] << w) }
  hasher.values
end