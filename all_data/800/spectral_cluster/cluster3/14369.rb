def combine_anagrams(words)

  res = Hash.new(0);
  
  words.each {
    |elem|
    idx = elem.downcase.chars.sort.join
    
    if res.has_key?(idx)
      list = res[idx]
      list << elem
      res[idx] = list
    else
      list=Array.new
      list.push(elem)
      res[idx] = list
    end
  } 
  return res.values
end