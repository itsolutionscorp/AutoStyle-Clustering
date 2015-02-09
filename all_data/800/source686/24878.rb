words = %w[cars for potatoes racs four scar creams scream]
def combine_anagrams(words)
res={}

words.each do |word|
  key=word.downcase.split('').sort.join
  res[key] ||= []
  res[key] << word
end

p res.values

  end
combine_anagrams(words)
