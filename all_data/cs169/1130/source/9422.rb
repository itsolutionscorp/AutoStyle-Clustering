#!/usr/bin/dev ruby

def combine_anagrams(words)
	previous_anagram = ""
	words_anagrams = {}
	group = []
	groups = []
	result = nil

	words_anagrams.compare_by_identity

	words.each { |item| words_anagrams[item] = item.downcase.scan(/./).sort.join }

	words_anagrams_original = words_anagrams.clone

	#puts "words_anagrams_original => #{words_anagrams_original}"
	#puts "words_anagrams => #{words_anagrams}"

	while words_anagrams.size > 0
		anagram = words_anagrams[words_anagrams.keys[0]]
		#puts "anagram => #{anagram}"

		if previous_anagram != "" && previous_anagram != anagram
			groups << group
			#puts "groups => #{groups}"

			group = []
		end

		#puts "words_anagrams.invert => #{words_anagrams.invert}"
		word = words_anagrams.invert[anagram]
		#puts "word => #{word}"
		group << word
		#puts "group => #{group}"

		if words_anagrams.size == 1
			groups << group
			#puts "groups => #{groups}"
		end

		words_anagrams.delete(word)
		#puts "words_anagrams => #{words_anagrams}"
		previous_anagram = anagram
		#puts "previous_anagram => #{previous_anagram}"
	end

	result = groups
end

#words = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
#puts "words => #{words}"
#puts
#puts "combine_anagrams words => #{combine_anagrams words}" # [["scar", "racs", "cars"], ["for"], ["potatoes"], ["four"], ["scream", "creams"]]
#puts
#words = ['cArs', 'for', 'potatOes', 'Racs', 'fOur', 'Scar', 'crEams', 'scream']
#puts "words => #{words}"
#puts
#puts "combine_anagrams words => #{combine_anagrams words}" # [["Scar", "Racs", "cArs"], ["for"], ["potatOes"], ["fOur"], ["scream", "crEams"]]
#puts

#words = ['a', 'b', 'a', 'd', 'c', 'b']
#puts "words => #{words}"
#puts
#puts "combine_anagrams words => #{combine_anagrams words}" # [["a", "a"], ["b", "b"], ["d"], ["c"]]
#puts

#words = ["cars", "cars", "racs", "scar", "four", "for", "potatoes", "creams", "scream", "scream"]
#puts "words => #{words}"
#puts
#puts "combine_anagrams words => #{combine_anagrams words}" # [["scar", "racs", "cars", "cars"], ["four"], ["for"], ["potatoes"], ["scream", "scream", "creams"]]
#puts

#words = ["pots", "spot", "stop", "tops", "tops", "spots", "stops", "sausage"]
#puts "words => #{words}"
#puts
#puts "combine_anagrams words => #{combine_anagrams words}" # [["tops", "tops", "stop", "spot", "pots"], ["stops", "spots"], ["sausage"]]
#puts