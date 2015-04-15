def combine_anagrams(words)
  groups = Hash.new{|hash, key| groups[key] = Array.new}
  words.each do |word|
    w = word.downcase
    stamp = w.chars.sort.join
    #if(!groups[stamp].include? word) 
      groups[stamp] << word
    #end
  end
  return groups.values
end

#puts combine_anagrams(['cars', 'Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
