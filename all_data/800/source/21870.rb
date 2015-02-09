def sort_word_chars (word)
	w_as_array = []
	out = ""
	word.downcase.each_char {|ch| w_as_array << ch	}
	w_as_array.sort!
	w_as_array.each { |ch| out << ch	}
	out
end

def combine_anagrams(words)
	out = []
	hashAnagrams = {}	
  words.each {|w| 
		wSorted = sort_word_chars w
		if hashAnagrams.has_key? wSorted
			hashAnagrams[wSorted] << w
		else	
			hashAnagrams[wSorted] = [w]
		end
	}
	
	hashAnagrams.each { |wSorted, arr| out << arr }
	out
end