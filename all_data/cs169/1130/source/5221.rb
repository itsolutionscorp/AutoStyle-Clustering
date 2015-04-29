def combine_anagrams(words)
  anawords = Array.new(words.length)
  result = Array.new
  for i in 0..words.length-1
    anawords[i] = words[i].downcase.split("").sort.join
  end
  for i in 0..words.length-1
    thisresult = Array.new
	  ende = false
	  for j in 0..words.length-1
	    if !ende && (anawords[j] == anawords[i]) then
	      if j < i then
		      ende=true
		    end
		    if j >= i then
		      thisresult << words[j]
		    end
	    end
	  end
	  if thisresult.length > 0 then
	    result << thisresult
	  end
  end
  return result  
end