def combine_anagrams(words)
  res = Array.new
  words.each do |w|
  	encontrado = false
  	res.each do |a|
  		if (a[0].chars.sort == w.chars.sort)
  		  a.push(w)
  		  encontrado = true
  		end
  	end
  	if (!encontrado)
  	 res.push(Array[w])
  	end
  end
  
  return res
end