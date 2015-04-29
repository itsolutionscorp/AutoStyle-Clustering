class DNA
  def initialize(dna)
    @dna = dna.upcase
  end

  def hamming_distance(dna_compare)
    count = 0
    dna1, dna2 = @dna.length < dna_compare.length ? [@dna, dna_compare] : [dna_compare, @dna]

    dna1.each_char.with_index do |nucleotide, index|
      if nucleotide != dna2[index]
        count += 1
      end
    end
    count
  end
end
