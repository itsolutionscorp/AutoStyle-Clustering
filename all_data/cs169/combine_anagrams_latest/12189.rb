class String
	def sort()
		self.chars.sort.join
	end
	def anagram?(string)
		if(self.downcase.sort == string.downcase.sort)
			return true
		else
			return false
		end
	end
end

def combine_anagrams(words)
	result = Array.new()
	#~ words.each{|word| words.each {|comp_word| if word!=comp_word then if word.anagram?(comp_word)==true 
	#~ words.each{|word|
	#~ group = Array.new().insert(0,word)
	#~ for comp_word in words
		#~ if word!=comp_word
			#~ if word.anagram?(comp_word)
				#~ group.insert(group.size,comp_word)
				#~ words.delete(comp_word)
			#~ end
		#~ end
	#~ end
	#~ result.insert(result.size,group)
	#~ }
	words.each{|word|
		group = Array.new().insert(0,word)
		words.each{|comp_word|
			if word!=comp_word
				if word.anagram?(comp_word)
					group.insert(group.size,comp_word)
					words.delete(comp_word)
				end
			end
		}
		result.insert(result.size,group)
	}
	return result

end

#~ puts combine_anagrams(['cars','for','potatoes','racs','four','ScAr','creams','scream'])
