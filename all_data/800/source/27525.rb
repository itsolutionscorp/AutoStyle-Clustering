def combine_anagrams(words)
#
ana = Hash.new([]);
words.each  { |w|
tempKey = w.downcase.chars.sort.join;
if ana.has_key?(tempKey)
 ana[tempKey] << w;
else 
 ana[tempKey] = [w]  ;
end
}
return ana.values;
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);
