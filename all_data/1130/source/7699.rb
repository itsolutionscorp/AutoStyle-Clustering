#!/usr/bin/ruby
# ------------------------------------------------------------------------
# A1:P3 (assignment1, part3) for coursera SaaS course
# ------------------------------------------------------------------------

#require 'pp'

=begin rdoc
Count Anagrams is a utility to find group of anagrams per word in the string array passed
=end

def combine_anagrams(words)
	#   <YOUR CODE HERE>

	anagrams = words.group_by { |word| word.downcase.chars.sort }.values
	p anagrams
	return anagrams

=begin
	#first attempt, commemted as arrived at a better concise way to do the same, tested to work similarly.
	grp_anagram={}
	words.each do |word|
  		key=word.downcase.split('').sort.join
		grp_anagram[key] ||= []
		grp_anagram[key] << word
	end

	p grp_anagram.values	
	return grp_anagram.values
=end

end

# Test for given input array in assignment
words = %w[cars for potatoes racs four scar creams scream] 
combine_anagrams(words)

# Test case in output is preserved, while including in same group of anagram
words = %w[CARS cars for potatoes racs four scar creams scream]
combine_anagrams(words)
