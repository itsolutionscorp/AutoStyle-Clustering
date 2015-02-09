def combine_anagrams(words)
res={}
words.each do |word|
  key=word.downcase.split('').sort.join
  res[key] ||= []
  res[key] << word
end

p res.values

end


input = %w[car  scar  for  acrs  four  creams  scream  racs]

combine_anagrams(input)
