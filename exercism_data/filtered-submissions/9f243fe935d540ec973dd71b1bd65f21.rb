class Hamming
  def compute(str1, str2)
  	a = str1.chars.zip(str2.chars)
  	b = 0

  	a.collect do |v| 
    	if v[0] != v[1] && v[1]
    		b += 1
   		end
   	end
    return b
	end
end
