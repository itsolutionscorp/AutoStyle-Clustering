def combine_anagrams(words)
  anagrams = Hash.new
  retArray = Array.new
  
  words.each do |word|
    downcased = word.downcase.chars.sort.join
	if anagrams.has_key?(downcased)
	  pos = anagrams[downcased]
	  retArray[pos].push word
	else
	  tmpArr = [word]
	  retArray.push tmpArr
	  anagrams[downcased] = retArray.length - 1
	end
  end
  
  return retArray
end