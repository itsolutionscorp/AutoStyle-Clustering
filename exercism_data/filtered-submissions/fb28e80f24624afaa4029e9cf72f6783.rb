def compute(seq1, seq2)

   dnaMinLength= [seq2.length, seq1.length].min
   seq1, seq2=seq1[0, dnaMinLength].bytes , seq2[0, dnaMinLength].bytes
   differentTotal=0

   dnaMinLength.times do |i|


      differentTotal=differentTotal+1 if seq1[i] % seq2[i] > 0
   end

   return differentTotal
  end