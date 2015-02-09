def combine_anagrams(words)
  words.group_by{|w| w.downcase.split(//).sort}.map{|k,v| v}
end

input=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
result=combine_anagrams(input)
print("Result: #{result}")
