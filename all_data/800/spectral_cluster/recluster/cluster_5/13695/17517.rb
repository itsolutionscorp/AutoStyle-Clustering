def combine_anagrams(words)
  if words == nil
    return words
  end
  if words.length == 0
    return words
  end
  
  swords = words.map{ |w|  [w.downcase.split(//).sort.join , w]  }
  owords = swords.sort
  
  road = owords.map{ |a,b| a}.uniq

  
  ret = []
  temp = []  
  prev = owords[0][0]
  owords.each{ 
    |a,b| 
	
	if  prev.eql?(a) 
	  temp.push(b) 
	else
   	  ret.push(temp.sort)
	  temp = []
	  temp.push(b) 
	end  
	
	prev = a
  }
  ret.push(temp)
  return ret
end
