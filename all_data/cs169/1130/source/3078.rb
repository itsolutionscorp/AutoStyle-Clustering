
def combine_anagrams(words)
	#   <YOUR CODE HERE>
	#~ puts "Original"
	#~ puts
	#~ puts words
	#~ puts words.size
	
	#sort the letters into a sorted array
	sorted = Array.new
	x = String.new
	y = String.new
	for i in 0..words.size-1  
		x = words[i].to_str
		y = x.downcase
		#~ puts y		
		#~ abort("yup")
		#~ y = x.downcase
		#~ x.downcase
		#~ puts y
		sorted.push(y.unpack("c*").sort.pack("c*"))
	end	
	#~ puts "---------"
	#~ puts "Sorted"	
	#~ puts
	#~ puts sorted
	#~ puts sorted.size
	#~ puts "*****************************************"	
	#now generate a new array
	anagram = Array.new
	while words.size > 0
		#initialise the new group
		group = Array.new
		group.push(words[0])
		#~ puts "---------"
		#~ puts "New Group"	
		#~ puts group.inspect

		#what it is when sorted
		x = words[0].to_str
		y = x.downcase
		sortval = y.unpack("c*").sort.pack("c*")
		#~ puts "sorted as " + sortval
		
		#copy
		for j in 1..words.size-1 
			if sorted[j]==sortval
				group.push(words[j])
				#~ puts "---------"
				#~ puts "Building the Group"	
				#~ puts group.inspect
			end	
		end
		anagram.push(group)
		#~ puts "---------"
		#~ puts "Anagram"	
		#~ puts
		#~ puts anagram.inspect
		#~ puts anagram.size
		#~ puts "______________"
		#clean
		#~ counter = 0
		j = 0
		while j<words.size
			#~ puts "J is now ", j			
			#~ puts "sorted = " ,sorted[j]
			if sorted[j]==sortval
				#~ puts "-to delete"
				#~ puts words[j]
				#~ puts "-----"
				words.delete_at(j)
				sorted.delete_at(j)
				#~ words.delete_at(j-counter)
				#~ sorted.delete_at(j-counter)
				#~ puts "new"
				#~ puts words.inspect
				#~ puts "______________"
				j = j - 1
			end
			j = j + 1
			#~ puts "J is now ", j
			#~ puts "***********************"
		end
		
		#~ puts "---------"
		#~ puts "Left over words"
		#~ puts
		#~ puts words.inspect
		#~ puts words.size
		
		#~ test = test + 1
	end	
	
	#~ puts "---------"
	#~ puts "Anagram"	
	#~ puts
	#~ puts anagram.inspect
	#~ puts anagram.size
	return anagram
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
puts words.inspect
puts combine_anagrams(words).inspect
words = ['fat','mumma']
puts words.inspect
puts combine_anagrams(words).inspect
words = []
puts words.inspect
puts combine_anagrams(words).inspect
words = ['cat','dog','cAt']
puts words.inspect
puts combine_anagrams(words).inspect
words = ['a','A']
puts words.inspect
puts combine_anagrams(words).inspect

