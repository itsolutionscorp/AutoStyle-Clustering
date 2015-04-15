def combine_anagrams(words)
	resultado = Hash.new
	words.each {|word| char_array = Array.new; word.downcase.chars {|char| char_array << char} ; sort_char_array = char_array.sort; resultado[sort_char_array] = (resultado[sort_char_array] == nil ? [word]:resultado[sort_char_array] << word)}
	resultado.values
end
