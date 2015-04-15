class Hamming
  class << self
    def compute(strand1, strand2)
      paired_nucleotides = pair_nucleotides(strand1, strand2)
      count_mutations(paired_nucleotides)
    end

    def pair_nucleotides(strand1, strand2)
      strand1_nucleotides = strand1.chars
      strand2_nucleotides = strand2.chars
      strand1_nucleotides.zip(strand2_nucleotides)
    end

    def count_mutations(paired_nucleotides)
      paired_nucleotides.count { |pair|
        mutation_detected?(pair)  
      }
    end

    def mutation_detected?(pair)
      nucleotide1, nucleotide2 = pair
      return false unless nucleotide2
      nucleotide1 != nucleotide2
    end
  end

  private_class_method :pair_nucleotides, :count_mutations,
    :mutation_detected?
end
