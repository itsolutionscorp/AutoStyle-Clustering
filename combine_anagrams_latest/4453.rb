def get_anagram_root(word)
	array = Array::new
	word.downcase.chars {
		|char|
		array << char
	}
	array.sort!
	hash_key = ''
	array.each_with_object( hash_key ){
	|char, hash_key|
	hash_key << char 
	}
	return hash_key
end

def combine_anagrams(words)
	hash = Hash::new
	words.each_with_object( hash ){
		|word, hash|
		hash_key = get_anagram_root( word )
printf %Q|word=#{word} hash_key= #{hash_key}\n|
		if not hash[ hash_key ]
			hash[ hash_key ] = [ word ]
		else
			hash[ hash_key ] << word
		end
	}
	return hash.values
end

#input = ['cars', 'for', 'potatOes', 'rAcs', 'four','scar', 'Creams', 'scream']

#printf %Q|combine_anagrams( #{input} ) returns :%s\n|, combine_anagrams( input )
