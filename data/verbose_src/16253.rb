def combine_anagrams(words)
  hash = Hash.new
  words.each do |e|
    key = e.downcase.chars.sort
    if hash.has_key?(key)
      hash[key]<<e
    else
      hash[key]=[e]
    end
  end
  array = Array.new
  hash.each_value do |e|
    array.push e
  end
  array
end

p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
