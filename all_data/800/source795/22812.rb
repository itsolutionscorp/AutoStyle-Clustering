#!ruby



def combine_anagrams(words)
	# transfering to lower-case and letter sorting
	map ={}
	
	words.each { |w|
		w1 = w.downcase
		key = w1.chars.sort.join; # sorted word
		if map.has_key?(key)==false
			map[key] = w1;
		else
			map[key] = [map[key], w1]
		end
		
	}
	
	list =[]
	
	map.each_value {|v|  
		list.push(v);	
	}	
	
	return list

end




#-------------------------------------------
# Running 
#-------------------------------------------

out = combine_anagrams(['caRS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
for i in 0..out.size-1
	puts " Anagrams #{out[i]}"
end










