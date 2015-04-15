class Hamming
   def self.compute(string1, string2)
      $id = 0
	  if string1.length > 0 && string2.length > 0
		  for pos in 0..string1.length - 1
			  if pos < string2.length
				 if string1[pos] != string2[pos]
					$id = $id + 1
				 end
			  else
				 break		  
			  end 
		  end 
	  end  
	  return $id
   end
end
