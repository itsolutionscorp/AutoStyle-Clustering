def combine_anagrams(words)

res={}
words.each do |word|
  key=word.split('').sort.join
  res[key] ||= []
  res[key] << word
end

return res.values
end