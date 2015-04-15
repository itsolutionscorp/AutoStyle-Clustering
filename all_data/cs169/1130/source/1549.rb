def combine_anagrams(words)
	ret = Array.new;
	no = 0
	for i in 0..words.length-1
		if no == 0 
			ret[0]= [words[i]]
			no+=1
		else
			new = 1
			for j in 0..no-1
				if ret[j][0].downcase.split('').sort() == words[i].downcase.split('').sort()
					new = 0
					ret[j] = ret[j] + [words[i]]
					break
				end
			end
			if new == 1
				ret[no] = [words[i]]
				no+=1
			end
		end
	end
	return ret
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])