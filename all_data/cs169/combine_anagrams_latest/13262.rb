def combine_anagrams(words)
  hash=Hash.new {|k, v| k[v] = []}  
  words.each do |word|    
    hash[word.downcase.chars.sort.join].push word        
  end
  
  hash.values
  
end


words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

puts "#{combine_anagrams words}"
