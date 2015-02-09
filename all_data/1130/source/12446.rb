def combine_anagrams(words)
  h = { }
  words.each do |w|
  	k = w.downcase.split('').sort.join
  	if h.has_key?(k)
  		h[k] << w
  	else
  		h[k] = [w]
  	end
  end
  
  a = [ ]
  h.each do |key,val|
  	a << val
  end

  return a
end