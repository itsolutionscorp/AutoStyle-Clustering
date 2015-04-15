def compute(seq1, seq2)

   dna_min_length= [seq2.length, seq1.length].min
   seq1, seq2=seq1[0, dna_min_length].bytes , seq2[0, dna_min_length].bytes
   different_total=0

   dna_min_length.times do |i|
      different_total=different_total+1 if seq1[i] % seq2[i] > 0
   end

   return different_total
  end