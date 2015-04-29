def combine_anagrams(words)
output=Hash.new
words.each do |word|
	if output.has_key?(word.downcase.chars.sort.join)
	output[word.downcase.chars.sort.join].push(word)
	else
	output[word.downcase.chars.sort.join]=[word]
	end
end
return output.values
end

#input=['cars','for','potatoes','racs','four','scar','creams','scream']
#puts combine_anagrams(input)

