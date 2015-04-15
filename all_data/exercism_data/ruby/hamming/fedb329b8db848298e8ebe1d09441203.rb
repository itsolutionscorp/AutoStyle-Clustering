#Reads in Two Strings, finds the smallest length.
#Creates two arrays of Bytes using the smallest length
#itterates over the array of Bytes comparing each numeric value at the stored location
#Comparison is done via the modulous operator, anything greater than 0 is not equal

class Hamming
  def self.compute(seq1, seq2)

   dna_min_length= [seq2.length, seq1.length].min
   seq1, seq2=seq1[0, dna_min_length].bytes , seq2[0, dna_min_length].bytes
   different_total=0

   dna_min_length.times do |i|
      different_total=different_total+1 if seq1[i] % seq2[i] > 0
   end #end of fo

   return different_total
  end #end of compute

end  #End of hamming
