# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	result = []
	words.each do |word|
		temp_word = sort_letters(word)
		is_found = false
		result.each do |grouped_array|
			if !false and sort_letters(grouped_array.last) == temp_word
				grouped_array << word
				is_found = true
			end
		end
		result << [word] if !is_found
	end
	result
end

def sort_letters(word)
	chars_array =[]
	word.each_char do |character|
		chars_array << character.downcase
	end
	chars_array.sort!
end
