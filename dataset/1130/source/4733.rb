def combine_anagrams(words)
  word_hash = Hash.new
  words.each do |word|
    key  = word.downcase.chars.sort.join
    p key
    p word
    if word_hash[key].nil?
      word_hash[key] = Array.new
    end            
    word_hash[key] << word 
    
  end
  p word_hash
  return word_hash.values
end