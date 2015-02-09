# input:


#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	retval={}
	words.each do |inpval|
		#puts inpval
		lcanag=inpval.downcase.chars.sort.join
		if retval[lcanag]==nil
			retval[lcanag]=[inpval]
		else
			retval[lcanag]<<inpval
		end 
	end 
	retarray=[]
	retval.each{|s,el|
#	puts el
#	puts "######"
		retarray<<el
	}
	return retarray
end

input=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#combine_anagrams(input)

