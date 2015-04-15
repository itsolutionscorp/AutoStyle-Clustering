def combine_anagrams(words)
	# <YOUR CODE HERE>
	anagrams = words.map{|word| word.downcase.split('').sort.join()}.uniq.map do |anagram| 
		words.select{|word| word if anagram.eql? word.downcase.split('').sort.join()}
	end
end
