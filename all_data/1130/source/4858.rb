#HW 1: Part 3
def combine_anagrams(words)
groups_arr = Array.new
swords = Array.new

words.each{ |word|
	
   	swords << word.downcase.split(//).sort.join
	
   	}
	
swords.each{ |sword|
	 idx = swords.index(sword)
   	 sub_arr = Array.new 
	 sub_arr<< words[idx]
	
#compare with other elements in the array
	 len = swords.length
	 for i in  0..len
		if ( i != idx)
         		if (sword == swords[i] )
			sub_arr<< words[i]
			end
		end
         end
	 
         groups_arr<<sub_arr
	}

return groups_arr.uniq

end
