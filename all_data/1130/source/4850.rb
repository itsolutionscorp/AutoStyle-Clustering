def combine_anagrams(words)
 
  anagrams = Hash.new

  words.each do |w|
    key = w.downcase.chars.sort.join
    
    if anagrams.has_key?(key)
      anagrams[key] << w 
    else
      anagrams[key] = [w]
    end
  end

  #return anagrams
  myArray = Array.new
  anagrams.each_value do |elem|
     myArray << elem
  end
  return myArray
end

