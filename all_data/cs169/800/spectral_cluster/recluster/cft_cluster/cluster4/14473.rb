
def combine_anagrams(words) 
	a = Hash.new;
	res = Array.new;
	
	words.each {|value| 
		str = value.downcase.split(//)
		str = str.sort
		str = str.join
		
		if a[str] == nil
			a[str] = Array.new
		end
		
		a[str].push(value)
	}
	
	a.each  {|key,value| 
		res.push(value);
	}
	
	return res

end 
 

