#Part 3, Anagrams,Stefan Veis Pennerup, kumuluzz@gmail.com, May 2012

def combine_anagrams(words)
	anagrams = []
	if words == []
	else
		words.each do |word|
			if anagrams.index{ |anagram| anagram.to_s.downcase.chars.sort.join == word.downcase.chars.sort.join }
				anagrams[anagrams.index{ |anagram| anagram.to_s.downcase.chars.sort.join == word.downcase.chars.sort.join }] << word
			else
				anagrams << [word].to_a
			end
		end
	end
	anagrams.each { |anagram| puts anagram.inspect }
	return anagrams
end

sjov = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
combine_anagrams(sjov)
