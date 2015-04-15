def combine_anagrams(words)
  group = 0
  dict = Hash.new
  #dict2 = Hash.new
  list = []
  words.each do |x|
    a = x.downcase.chars.to_a.sort
    if (dict.has_key?(a))
      list[dict[a]] += [x] 
    else
      dict[a]  = group
      list[group] = [x]
      group += 1
    end
  end
  return list
end

p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
