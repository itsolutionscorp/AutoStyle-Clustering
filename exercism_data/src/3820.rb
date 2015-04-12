class Hamming

  def compute(dna_strand_1, dna_strand_2)
    hamming_distance = 0
    dna_strand_1.each_char.with_index do |nucleic_acid, index|
      hamming_distance += 1 if nucleic_acid != dna_strand_2[index]
    end
    hamming_distance
  end

end
