def combine_anagrams(words)
  hash = {}
  words.sort.each do |word| 
    key = word.strip.upcase.split(//).sort
    hash[key].nil? ? hash[key] = [word] : hash[key] << word
  end
  hash.values
end