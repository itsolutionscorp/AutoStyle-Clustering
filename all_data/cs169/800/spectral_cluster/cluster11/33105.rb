
def combine_anagrams(words)
  anagrams = Hash.new { |hash, key| hash[key] = Array.new }
  
  words.each do |word|
    key = word.downcase.chars.sort.join
    anagrams[key].push(word)        
  end
  
  result = Array.new
  
  anagrams.each do |key,value|
    result.push(value);
  end
  
  result
end