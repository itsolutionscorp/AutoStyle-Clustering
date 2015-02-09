def combine_anagrams(words)
  
  if words.empty?
    return words
  end
  
  anagrams = {} # Hash.new([])
  words.each do |word|
    key = word.downcase.split(%r{\s*}).sort.join
    anagrams[key] ||= []
    anagrams[key] << word
  end
  anagrams.values
end

test1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print combine_anagrams(test1) #[["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

test2 = ['Cars', 'for']
print combine_anagrams(test2)

test3 = ['a','b']
print combine_anagrams(test3)

test4 = []
print combine_anagrams(test4)