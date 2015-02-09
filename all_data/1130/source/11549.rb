def combine_anagrams(words)
index=Hash.new
data=Array.new
count=0
words.each do |word|
	normalized_word=word.downcase.chars.sort.join
	if index[normalized_word] == nil 
		index.store(normalized_word, count)
		data << Array[word]
		count+=1
	else
		data[index[normalized_word]] << word
	end
end
data
		
end