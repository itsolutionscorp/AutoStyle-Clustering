#Reads in Two Strings, finds the smallest length.
#Creates two arrays of Bytes using the smallest length
#itterates over the array of Bytes comparing each numeric value at the stored location
#Comparison is done via the modulous operator, anything greater than 0 is not equal

class Hamming
  def compute(seq1, seq2)

   dnaMinLength= [seq2.length, seq1.length].min
   seq1, seq2=seq1[0, dnaMinLength].bytes , seq2[0, dnaMinLength].bytes
   differentTotal=0

   dnaMinLength.times do |i|
  #for i in 0..dnaMinL  length
      #differentTotal=differentTotal+1 if seq1[i] != seq2[i] #old verison
      differentTotal=differentTotal+1 if seq1[i] % seq2[i] > 0
   end #end of fo

   return differentTotal
  end #end of compute

end  #End of hamming