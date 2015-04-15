def combine_anagrams(words)
  dict = {}
  words.each { |w| dict[w.downcase.split(//).sort] = [] }
  words.each { |w| dict[w.downcase.split(//).sort] << w }
  dict.values
end
