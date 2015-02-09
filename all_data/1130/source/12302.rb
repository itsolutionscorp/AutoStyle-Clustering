# jxitc part 3 assignment 1

def get_code(word)
	s = word.downcase
	s = s.split("")
	s = s.sort()
	code = s.join()
	return code
end

def combine_anagrams(words)
	puts "Process words array #=#{words.length}"
	hash = {}
	
	words.each do |word|
		code = get_code(word)
		if not hash.include?(code)
			hash[code] = []	
		end

		list = hash[code]
		list << word
	end

	puts hash.length
	rsltList = []
	
	hash.each do |code,word_list|
		rsltList << word_list
	end
	puts "rsltList.length = #{rsltList.length}"
	return rsltList
end

if __FILE__ == $0
	words = ['cars', 'for', 'potatoes', 'racs',
					 'four','scar', 'creams', 'scream']

	puts combine_anagrams(words)
end
