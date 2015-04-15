def combine_anagrams(words)
  words.uniq { |s| s.downcase.split('').sort.join }.collect { |w| words.select { |n| n.downcase.split('').sort.join == w.downcase.split('').sort.join}}
end
