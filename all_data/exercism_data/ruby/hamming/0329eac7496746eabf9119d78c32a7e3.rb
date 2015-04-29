class Hamming

  def self.compute(reference_dna, comparing_dna)
    difference_nucleic_acid = 0

    # Start with shorten
    source_dna = reference_dna.length > comparing_dna.length ? comparing_dna : reference_dna
    target_dna = reference_dna.length > comparing_dna.length ? reference_dna : comparing_dna

    source_dna.each_char.to_a.each_with_index do |nucleic_acid, index|
      difference_nucleic_acid += 1 unless source_dna.each_char.to_a[index] == target_dna.each_char.to_a[index]
    end

    difference_nucleic_acid
  end

end
