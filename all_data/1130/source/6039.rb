#!/usr/bin/env ruby

def combine_anagrams(words)
	letters=[]
	final=[]
	words.each do |word|
		ar=[]
		word2=word.downcase
		word2.each_char do |chr|
			ar.insert(-1,chr)
		end
		#sort letters
		ar.sort!
		#the array with letters (letters) has unique elements (sorted letters)
		if(letters.include?(ar))
		else
			letters.insert(-1,ar)
			final.insert(-1,[])
		end
		#the index where the word's letters are ar letras is the array of final where it should be
		final[letters.index(ar)].insert(-1,word)
	end
	final
end

#print combine_anagrams(['cars', 'fOr', 'potatoes', 'racs', 'four','scar', 'creams','scream'])