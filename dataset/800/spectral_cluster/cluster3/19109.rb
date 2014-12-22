#!/usr/bin/env ruby

def is_anagram(reference,compared)
	puts 'reference sorted: '+reference.chars.sort.join
	puts 'compared sorted: '+compared.chars.sort.join 
	(reference.downcase.chars.sort.join<=>compared.downcase.chars.sort.join) == 0
end

def combine_anagrams(words)
	result = Hash.new
	words.each do |word|
		sortedword = word.downcase.chars.sort.join
		tmp = result[sortedword]
		if(tmp != nil)
			puts 'adding word "'+word+'" to hash'
			result[sortedword] = tmp + [word]
		else
			puts 'creating new hash entry with word "'+word+'"'
			result[sortedword] = [word]
		end
	end
	return result.values
end

=begin
input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
h = combine_anagrams(input)
h.values.each do |element|
	print 'element: '
	element.each do |subelement| 
		print 'sub-element: '
		puts subelement
	end
end
=end
