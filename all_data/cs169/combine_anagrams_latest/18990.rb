def combine_anagrams(words)
  result = Array.new
  
  while words.size !=0
	string = words[0]
	subArray = takeAnagrams(string, words)
	words = words-subArray
	result.insert(-1,subArray)	
  end
  result
 end

def takeAnagrams(theString, array)
  result = Array.new
  result[0]=theString
  

  sorted = theString.downcase.scan(/./).sort.join	
  for i in 1..array.size-1
	matchString = array[i].downcase.scan(/./).sort.join
	if sorted == matchString
	  result.insert(-1, array[i])
	end
 
  end
  result
  
end  