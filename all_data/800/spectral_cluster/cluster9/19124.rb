# Okay, I need to build a method that takes an array of strings (so I don't have to worry about parsing!) and groups them according to whether or not they are anagrams.

#step 1, 
def combine_anagrams(words)
	myhash = {}
	# n = words.length 
	# for i in 0..n
	# 	puts words[i]
	# end
	words.each do |word|
		sorted = word.downcase.split(%r{\s*}).sort.join
		# sorted = sorted.sort.join
		# puts sorted
		if myhash.key?(sorted)
			myhash[sorted] = myhash[sorted], word
		else
			newhash = {sorted=>word}
			myhash = myhash.merge(newhash)
		end
		# output << word.downcase.chars.sort.join #this sorts each word by its characters.  Now I need to find a way to compare this value with other members of the array.  Do I need to create a new array?  The dilemma comes because I need to maintain the original words and not just end up with a list of sorted letters.

		#Possible solutions.  I can create an array that has all of the words sorted by their letters.  Then I can compare take the original words array, take each element at a time, sort it by its letters, compare it to the sorted list, and add it to a third array when it finds its match.  Basically, the sorted array acts as a key.  The third 'array' should actually be a hash-table, with each key representing one unique sorted word.  Then my matching method will stick the actual word in as a value for that key.  The last step will be converting the hash-table to an array by essentially making each hash an array.  I think this will work but it sounds pretty darn convoluted.  

	end

	# Perfect!  This has created a hash-table that contains all the info I need, sorted in the manner I need.  Now we just need to convert it into an array filled with other arrays...
	myarray = []
	myhash.each_value {|value| myarray << value}
	return myarray
	# puts myarray[1]
	# puts '***************'
	# puts myarray[2]
	# puts '***************'
	# puts myarray[3]
	# puts '***************'
	# puts myarray[4]
	# puts '***************'
	# n = myhash.length
	# myhash.each do |key|
	# 	puts key[key]
	# end
end

test = ['cars', 'for', 'potatoes', 'Racs', 'four','scar', 'creams', 'scream']
combine = combine_anagrams(test)
puts combine