# Part 3: Anagrams
# Author: Denver Sessink, 2012
# input: 
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  
# [
# 	["cars", "racs", "scar"], 
# 	["four"], 
# 	["for"], 
# 	["potatoes"], 
# 	["creams", "scream"]
# ]

def combine_anagrams(words)
  anagrams = []

  words.each { 
  	|word| 
  	
	sorted_word = word.downcase.chars.sort.join

  	# controleer of het gesorteerde woord in 1 van de arrays in anagrams voor komt. 
  	# voeg hem toe aan de array met het overeenkomende anagram
  	# anders voeg hem als nieuwe array toe aan anagrams
  	toegevoegd = false

  	# for i in anagrams.length do |anagram_list|
  	anagrams.map {
  		|anagram_list|

  		if sorted_word == anagram_list[0].downcase.chars.sort.join
  			anagram_list << word
			toegevoegd = true	
  		end
  	}

  	if toegevoegd == false
  		anagrams << [word]
  	end

  }

  anagrams
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'A', 'a', 'Hello', 'HeLLo'])