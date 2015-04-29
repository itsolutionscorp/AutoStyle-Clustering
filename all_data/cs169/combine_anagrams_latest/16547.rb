def combine_anagrams(words)
  anagrams = {} # Hash.new([])
  words.each do |word|
    key = word.split(%r{\s*}).sort.join
    anagrams[key] ||= []
    anagrams[key] << word
  end
  anagrams.values
end
 
combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])