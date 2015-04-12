def compute(seq1, seq2)

   dnaMinLength= [seq2.length, seq1.length].min
   seq1, seq2=seq1[0, dnaMinLength].bytes , seq2[0, dnaMinLength].bytes
   differentTotal=0

   dnaMinLength.times do |i|
  #for i in 0..dnaMinL  length
      #differentTotal=differentTotal+1 if seq1[i] != seq2[i]
      differentTotal=differentTotal+1 if seq1[i].to_f % seq2[i].to_f > 0
   end #end of fo

   return differentTotal
  end #end of compute