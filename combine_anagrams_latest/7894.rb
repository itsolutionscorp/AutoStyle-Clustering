def combine_anagrams(words)
  unique_hash = {}
  hash = []
  words.each do |word|
   if unique_hash[word.downcase.chars.sort.join] == nil
    unique_hash[word.downcase.chars.sort.join] = unique_hash.length
   end
   if hash[unique_hash[word.downcase.chars.sort.join]] == nil
    hash[unique_hash[word.downcase.chars.sort.join]] = [word]
   else
    hash[unique_hash[word.downcase.chars.sort.join]] << word
   end
   
  end
  return hash
end