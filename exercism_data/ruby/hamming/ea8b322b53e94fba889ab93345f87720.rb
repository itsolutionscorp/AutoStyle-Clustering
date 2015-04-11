class Hamming
  def self.compute(first_dna, second_dna)
    shorter_dna_length = (first_dna.length < second_dna.length) ? first_dna.length : second_dna.length
    nucleotides_range = 0..(shorter_dna_length - 1)
    hamming_distance = 0
    for single_nucleotide in nucleotides_range
      hamming_distance += 1 if first_dna[single_nucleotide] != second_dna[single_nucleotide]
    end
    hamming_distance
  end
end
