def combine_anagrams(words)
  hash = {}
  words.each do |word|
    key = word.downcase.split('').sort.join('')
    hash[key] ||= []
    hash[key] << word
  end
  hash.values
end

expected = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]].sort
solution = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).sort
solution == expected || raise(Exception.new("Expected: #{expected.inspect} got #{solution.inspect}"))
