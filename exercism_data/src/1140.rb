class Hamming

	def compute(string1, string2)
	  count = 0
	  
		string1.length.times do |n|
		   string1_letter = string1.split(//)[n]
		   string2_letter = string2.split(//)[n]

			if string1_letter == string2_letter
				  count += 0
			  else 
			  	count += 1
		  end
		end
	  return count
	end
end
