def combine_anagrams(words)
result = []
words.each do |i|
x = []
words.each do |j|
if (i.downcase.chars.sort.join == j.downcase.chars.sort.join) 
x << j
end
end
if !result.include?(x)
result << x
end
end
return result
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
combine_anagrams(words)