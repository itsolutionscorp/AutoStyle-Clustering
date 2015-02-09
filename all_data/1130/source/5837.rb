def combine_anagrams(words)
  hash = Hash.new
  words.each do |w|
    s = w.downcase.chars.sort.join
    if hash.has_key? s
      #puts hash{s}
      hash[s] << w
    else
      hash[s] = [w]
    end
  end
  
  returnArray = []
  
  hash.each_value do |val|
    returnArray << val
  end
  
  return returnArray
end
