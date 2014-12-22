def combine_anagrams(words)

  anagram_hash = Hash.new(0)
  anagram_array = Array.new()
  
  words.each do |x|
  
    sorted = x.downcase.split(//).sort.to_s
    
    if anagram_hash.has_key?(sorted)
      anagram_hash[sorted].push(x)
    else
      anagram_hash[sorted] = Array.new().push(x)
    end
    
  end
  
  anagram_hash.each do |key, value|
    anagram_array.push(value)
  end
  
  return anagram_array
  
end

p combine_anagrams(['CARS', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])
