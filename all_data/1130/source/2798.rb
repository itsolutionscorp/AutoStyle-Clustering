
def combine_anagrams(words)
  words.map { |w| puts w; words.select { |p| p.downcase.chars.sort.join == w.downcase.chars.sort.join } }.uniq
end

