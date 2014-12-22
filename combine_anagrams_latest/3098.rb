def combine_anagrams(words)
  newArr = []
  wordsUsed = []
  
  words.each_index{ |i|
	if wordsUsed.include?(words[i]) == false
		tmpArr = []
		tmpArr.push(words[i])
		
		(i+1..words.length-1).each{ |xi|
			if words[i].chars.sort{ |a, b| a.casecmp(b) }.join.downcase == words[xi].chars.sort{ |a, b| a.casecmp(b) }.join.downcase
				tmpArr.push(words[xi])
				wordsUsed.push(words[xi])
			end
		}
		
		newArr.push(tmpArr)
	end	
  }
  
  return newArr
end