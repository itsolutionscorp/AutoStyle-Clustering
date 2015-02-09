def combine_anagrams(words)
  map = {}
  words.each do |elt| 
    key = elt.downcase.split('').sort.to_s
    anagrams = map[key].to_a << elt;
    map[key] = anagrams
  end
  map.collect { |key, value| value }
end
