class Hamming
  def compute(dna_1, dna_2)
    mismatches = 0
    dna_1.size.times do |i|
      mismatches += 1 if dna_1[i] != dna_2[i]
    end 
    mismatches
  end
end
