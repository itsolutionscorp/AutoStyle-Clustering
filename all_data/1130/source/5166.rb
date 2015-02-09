#!/usr/bin/ruby
##############################################
# Author: Michael Gerber
# Date:    5/28/12
# Assignment: Home Work 1
##############################################
# Part 3 Anagram
##############################################

def combine_anagrams(words)
	swords = Array.new
	noDups = Array.new
	groupWords = Array.new
	anagrams = Array.new
	words.each {|word| swords << word.downcase.chars.sort.join}
	swords.each{|word| noDups << word unless !noDups.index(word).nil? }
	noDups.each do|tword|
					
					groupWords = Array.new
					words.each {|word|  groupWords << word unless word.downcase.chars.sort.join != tword}
					anagrams << groupWords
				end
				
	return anagrams
end



w = ['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']


combine_anagrams(w)