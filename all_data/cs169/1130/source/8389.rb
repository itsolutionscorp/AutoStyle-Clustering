def combine_anagrams(words)
#
result = Array.new
sorted = Array.new
words.each do |word|
  sorted = sorted << word.downcase.split(//).sort.join.to_s
end
i = 0
sorted.each do |sortedword|
  if result[sorted.index(sortedword)] == nil
    result[sorted.index(sortedword)] = Array.new
    result[sorted.index(sortedword)] << words[i]
  else
    result[sorted.index(sortedword)] << words[i]
  end
  i = i + 1
end
return result.compact
end



#a = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#a = ['Cars', 'for', 'potAtoes', 'rAcs', 'four','scar', 'creams', 'scrEam']
#a = ['c','f','p','f','s','c','s']
#a = ['c']
#aa = ['Cars', 'for', 'potAtoes', 'rAcs', 'four','scar', 'creams', 'scrEam', 'aabbcc']
#aa = 'aabbcc'
#puts "FINAL RESULT"
#puts combine_anagrams(aa).inspect
#puts combine_anagrams(a).inspect