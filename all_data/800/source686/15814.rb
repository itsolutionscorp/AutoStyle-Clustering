#!/usr/bin/ruby

def combine_anagrams(words)
	# my code here
	temp = words.collect{|x| x.downcase.chars.sort.to_s}
	#temp = words.group_by( 
	temp.uniq!

	final = temp.collect do |alph| 
		temp2 = []
		words.each do |x|
			if ( x.downcase.chars.sort.to_s == alph )
			then temp2 << x
			end
		end
		temp2
	end

	final
	# end my code
end

# test = ['CARS', 'FOR', 'POTATOES', 'RACS', 'FOUR', 'SCAR', 'CREAMS', 'SCREAM']

# combine_anagrams(test)

# password for the site I bought the book from:
# ihatenewpasswords!!1234
