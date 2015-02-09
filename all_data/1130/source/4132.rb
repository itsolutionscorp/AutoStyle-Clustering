def combine_anagrams(words)
 words_hash = words.each_with_object(Hash.new []) do |word, hash|
  hash[word.downcase.chars.sort] += [word]
end
 x=[]
 words_hash.each do |k,v|
   x<<v
 end
 return x
end