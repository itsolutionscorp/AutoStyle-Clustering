def combine_anagrams(words)
  hash = Hash.new
  words.each do |w|
    key = w.downcase.chars.sort.join
    value = hash[key]
    if value == nil
      hash[key] = [w]
    else
      hash[key] = value.push(w)
    end
  end
  
  return hash.values
end

w = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams(w)