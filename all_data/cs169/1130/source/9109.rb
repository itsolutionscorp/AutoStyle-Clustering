def combine_anagrams(words)
#<YOUR CODE HERE>

hash = Hash.new;

words.each { |word| 
  key = word.chars.sort.join.downcase;
  if hash[key] == nil
    hash[key] = [word];
  else
    hash[key] << word;
  end
  
  hash.values.to_s;
}

end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
