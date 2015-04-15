def combine_anagrams(words)
  
  ana = 
  words.each_with_object(Hash.new []){ |x, hash|
    hash[x.downcase.chars.sort] += [x]
    }
  
  ana.values
end