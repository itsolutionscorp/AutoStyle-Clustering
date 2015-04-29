#!/bin/ruby

class String
	def sort
        	self.split(//).sort.join
             	# maybe better to use #sort! to avoid some 
             	# garbage being created
	end
end

def combine_anagrams(words)
	return words.map{ |item| item.upcase.sort }.uniq.map{ |item| words.select{ |word| word.upcase.sort.eql?(item) } }
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'ScAr', 'creams', 'scream', 'ScReAm'] )
