def combine_anagrams(words)
#
	h= Hash.new
	words.each do|word|
    	key = word.downcase.split('').sort.join
    	value=h[key]
    	if value==nil
    		h[key]=Array.new<<word
    	else
    		h[key]=value<<word
    	end
	end
	ret=Array.new
	h.each do |key,value|
       ret<<value
	end
	ret
end