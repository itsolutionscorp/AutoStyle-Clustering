def combine_anagrams(words)

res={}

words.each do |word|
  key=word.split('').sort.join
  res[key] ||= []
  res[key] << word
end

p res.values

end


wordsList = %w[cars for potatoes racs four scar creams scream]
combine_anagrams(wordsList)
