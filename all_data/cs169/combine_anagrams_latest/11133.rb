def combine_anagrams(words)
 result = Array.new    
 if words.size<2 
    return result
 end   
  myHash = Hash.new
  words.each_index do |x|
    string=words.at(x).downcase
    sorted_word = string.chars.sort.join  
    unless myHash.has_key?(sorted_word)                      
    myHash[sorted_word] = Array.new()
    end
  myHash.fetch(sorted_word).push(words.at(x))              
  end
myHash.each_value {|value| result.push(value) }
return result
end
