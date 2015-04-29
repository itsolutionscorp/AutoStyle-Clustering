def combine_anagrams(words)
	
	results = []
	words.each do |word| 
		# reorder the current word
		wordSorted = word.chars.sort { |a, b| a.casecmp(b) } .join
		
		if results.flatten.length == 0
			sub = []
			sub.push(word)
			results.push(sub)
		else
			# look in each sub array. Rearrange the first word in the sub array
			# and compare that with the requested word. If they match, push
			# into sub array. Otherwise, continue searching into the next subarray
			# or create a new subarray
			
			# variable to let us know we have added something to our new results list
			# NOTE: there should be a better way of doing this 
			modified = false
			results.each do |subarray|
				subWordSorted = subarray[0].chars.sort { |a, b| a.casecmp(b) } .join
				if wordSorted == subWordSorted
					subarray.push(word)
					modified = true
					break
				end
			end
			p modified 
			if modified != true
				sub = []
                        	sub.push(word)
                        	results.push(sub)
			else
				modified == false
			end
		end
	end
	p results
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
