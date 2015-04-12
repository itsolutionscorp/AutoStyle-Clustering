class Hamming
  def compute(dna_1, dna_2)
    mismatches = 0
    smaller_str_size = dna_1.size < dna_2.size ? dna_1.size : dna_2.size
    smaller_str_size.times do |i|
      mismatches += 1 if dna_1[i] != dna_2[i]
    end 
    mismatches
  end
end
