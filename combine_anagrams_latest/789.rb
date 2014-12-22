def combine_anagrams(words)
  
  anagrams = Hash.new
  
  words.each do |word|
    key = word.downcase.split('').sort.join.to_sym
    anagrams[key] = [] if anagrams[key].nil?
    anagrams[key] << word 
  end
  
  anagrams.values
end