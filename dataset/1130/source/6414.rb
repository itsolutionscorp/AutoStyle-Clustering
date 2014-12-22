def combine_anagrams(words)
 
anagrams = {}
if words == nil then
  return anagrams.values
end 
 words.each do |word|
  oword = word
  word = word.downcase
  hashkey = word.split('').sort.join
  anagrams[hashkey] ||= []
  anagrams[hashkey] << oword
 end
return anagrams.values
end


