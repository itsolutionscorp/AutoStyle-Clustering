def combine_anagrams(words)
  
  kv = Hash.new
  words.each do |word|
  
    key = word.downcase.chars.sort.join
    if kv.has_key?(key)
      kv[key] = kv[key] + [word]
    else
      kv[key] = [word]
    end
  
  end

  
  return kv.values()
  
end