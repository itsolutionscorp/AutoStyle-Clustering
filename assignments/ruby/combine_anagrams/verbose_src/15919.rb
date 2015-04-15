def combine_anagrams(words)
res={}

words.each do |word|
  key=word.split('').sort.join
  res[key] ||= []
  res[key] << word
end

p res.values
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])