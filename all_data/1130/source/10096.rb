def combine_anagrams(words)
  h = {}
  words.each { |w| (h[w.downcase.split("").sort.join] ||= []) << w}
  h.values
end
