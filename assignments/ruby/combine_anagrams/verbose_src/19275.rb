#anagram
require 'pp'
def combine_anagrams(words)
  input = words
	hash = {}
	sorted = Array.new
	input.each do |i|
		sorted << i.downcase.chars.sort.join
	end
	unique = sorted.uniq
	unique.each do |s|
		hash[s] =Array.new
		sorted.each_index do |x|
			if sorted[x]==s
				hash[s]<< words[x]
			end
		end	
	end	
	output = Array.new
	hash.each_value {|value| output<< value}	
	pp output
end
	combine_anagrams ['cars', 'for', 'potatoes', 'rAcs', 'Four','scar', 'creams', 'Scream'] 