def combine_anagrams(words)

 words.group_by {|word| word.downcase.chars.sort.join}.collect {|k,v| v} 

end


w=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

x=combine_anagrams(w)
x