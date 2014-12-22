def combine_anagrams(words)

	res=[]

	words.each {|word|

	if not res.any? {|anagrams|
 
	if anagrams[0].split(//).sort == word.split(//).sort

	anagrams.insert(anagrams.length,word)

	true

	else

	false

	end}

	res.insert(res.length,[word])

	end}

	res

end
