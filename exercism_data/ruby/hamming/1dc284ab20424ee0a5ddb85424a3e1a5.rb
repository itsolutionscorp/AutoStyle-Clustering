class Hamming
  def self.compute(dna_strand_one, dna_strand_two)
    pair_of_nucleotides(dna_strand_one, dna_strand_two).reduce(0) do |hamming_distance, nucleotides| 
      nucleotide_one, nucleotide_two = nucleotides
      if point_mutation_found(nucleotide_one, nucleotide_two)
        hamming_distance + 1
      else
        hamming_distance
      end
    end
  end

  def self.point_mutation_found(nucleotide_one, nucleotide_two)
    nucleotide_one != nucleotide_two
  end

  def self.pair_of_nucleotides(dna_strand_one, dna_strand_two)
    trim_to_shorter_strand(nucleotides_of(dna_strand_one).zip(nucleotides_of(dna_strand_two)))
  end

  def self.trim_to_shorter_strand(pair_of_nucleotides)
    pair_of_nucleotides.reject { |_, second_nucleotide| second_nucleotide.nil? }
  end

  def self.nucleotides_of(dna_strand)
    dna_strand.chars
  end
end
