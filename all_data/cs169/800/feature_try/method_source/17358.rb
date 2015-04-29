def combine_anagrams(words)
  h = {}
  words.each do |word|
    sword = word.downcase.chars.sort.join
    (h[sword] == nil) ? (h[sword] = [word]) : ((h[sword] << word))
  end
  return h.values
end