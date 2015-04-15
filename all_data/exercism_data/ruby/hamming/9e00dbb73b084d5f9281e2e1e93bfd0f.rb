class Hamming
 def self.compute (dna1 , dna2)
  hamming_distance = 0
  length = dna1.length
  if dna1.length > dna2.length
   length = dna2.length
  end  
  for i in 0..length-1
   if dna1[i]!=dna2[i]
    hamming_distance = hamming_distance + 1
   end
  end 
 return hamming_distance  
 end
end
