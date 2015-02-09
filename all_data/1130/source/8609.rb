def combine_anagrams(words)
  #   <YOUR CODE HERE>
 
  arrFinal = []
  while words.length > 0
	arr = []
	i = 0
	arg0 = words.first
	arr.push(arg0)
	words.delete_at(words.index(arg0))
		while i < words.length
			
			if arg0.downcase.chars.sort.join == words[i].downcase.chars.sort.join
				arr.push(words[i])	
				words.delete_at(words.index(words[i]))
			else
				i += 1
			end
		end
		# words.delete(words.first)	
	arrFinal.push(arr)
  end
# puts "arrfinal " + arrFinal.to_s
  return arrFinal
  end
  
#   puts combine_anagrams(['carS', 'For', 'poTatoes', 'rAcs', 'foUr','scar', 'creams', 'scReaM','ReamCs','RouF'])
# puts combine_anagrams(['creams','scReaM','ReamCs','reamcs'])
