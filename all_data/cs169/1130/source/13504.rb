def combine_anagrams(words)
  output = Hash.new{ |hash, key| hash[key] = Array.new;}
  
  words.each do |word|
    key = word.downcase.chars.sort { |a, b| a.casecmp(b) } .join # case-insensitive string sort
    output[key].push(word)
  end
  
 output.values
end