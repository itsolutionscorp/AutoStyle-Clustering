class Hamming

  def compute(first_dna_strand, second_dna_strand)
    hamming_distance = 0

    first_dna_strand.split(//).each_with_index do |nucleotide, index|
      unless nucleotide == second_dna_strand.split(//)[index]
        hamming_distance += 1
      end
    end

    hamming_distance
  end

end
