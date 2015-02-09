
def ex()
	['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
end

def is_anagrams(s1,s2)
	s1=s1.downcase.chars.sort.join 
	s2=s2.downcase.chars.sort.join
	s1==s2
end

def combine_anagrams(words)
	combine_anagrams_iter(words,[])	
end

def combine_anagrams_iter(words,accum)

	if words.empty? then return accum end

	firstWord = words[0]
	words.delete_at(0)
	anagrams = words.select {|word| is_anagrams(firstWord,word)}
	newWords = words.delete_if {|word| anagrams.include? word}

	combine_anagrams_iter newWords,accum<<(anagrams<<firstWord)

end

def t(w)
	combine_anagrams(w)	
end 
