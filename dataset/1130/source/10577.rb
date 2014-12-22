def combine_anagrams(words)
  hash = Hash.new [] 
  words.each do |w|
    characters = w.downcase.chars.sort
    if hash.include?(characters) 
      hash[characters].push(w)
    else
      hash[characters] = [w]
    end    
  end
  hash.values
end
