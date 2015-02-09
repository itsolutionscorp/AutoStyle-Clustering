def combine_anagrams(input)
result = Array.new(1)
output = Array.new(2)
input.each do |word|
tempWord=word.downcase.chars.sort.join
if (result.index(tempWord)==nil)
result.concat([tempWord])
end
i=result.index(tempWord)
if (output[i]==nil)
output[i]=[word]
else
output[i]<<word
end
end
output-[nil]
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
