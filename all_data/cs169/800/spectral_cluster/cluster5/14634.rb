def combine_anagrams(words)
  groups = Hash.new{|h,k| h[k]=[]}
  words.inject(groups) {|h,k| h[k.upcase.chars.sort.join]<<k; h}.values
end

#a=combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])
