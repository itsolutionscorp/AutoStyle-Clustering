def combine_anagrams(words)

sorted = Hash.new
final = Hash.new {|h,k| h[k]=[]}

words.each { |word| sortedword = word.downcase.chars.sort.join;  sorted[word] = sortedword;}
sorted.each { |k,v|  final[v].push k }

return final.values

end

arr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams(arr)