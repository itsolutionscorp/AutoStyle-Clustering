#!/bin/ruby

class String
	def sort
		self.split(//).sort.join('')
	end
end

def anagrams? word1,word2
	word1.downcase.sort==word2.downcase.sort
end

def combine_anagrams(words)
	groups=[]
	words.each{|word|
		if groups.flatten.index{|word2|anagrams? word, word2}
			groups.each_with_index{|group,i|
				if group.first.downcase.sort == word.downcase.sort
					groups[i] = group + [word]
				end
			}
		else
			groups += [[word]]
		end
	}
	groups
end

=begin
test = ['cars', 'a', 'A', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(test).inspect
=end
