def combine_anagrams(words)
  canonical = words.map {|word| word.downcase.split('').sort.join}
  
  groups = {};
  groups.default = [];
  words.zip(canonical) {|pair| groups[pair[1]] = groups[pair[1]] + [pair[0]]}
  groups.values;
end