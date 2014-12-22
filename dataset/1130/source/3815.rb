#!/usr/bin/env ruby

def combine_anagrams(words)
	anagrams = Hash.new([])
	words.each { | word | 
				anagrams[word.downcase.chars.to_a.sort.to_s] += [word] 
			   }
	anagrams.values
end