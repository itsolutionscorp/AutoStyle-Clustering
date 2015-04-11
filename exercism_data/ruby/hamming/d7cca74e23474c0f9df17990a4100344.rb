class Hamming
  def self.compute(strand1, strand2)
    paired_nucleotides = pair_nucleotides(strand1, strand2)
    count_mutations(paired_nucleotides)
  end

  def self.pair_nucleotides(strand1, strand2)
    strand1_nucleotides = strand1.split("")
    strand2_nucleotides = strand2.split("")
    strand1_nucleotides.zip(strand2_nucleotides)
  end

  def self.count_mutations(paired_nucleotides)
    paired_nucleotides.keep_if { |pair|
      mutation_detected?(pair)  
    }.count
  end

  def self.mutation_detected?(pair)
    return false unless pair[0] && pair[1] 
    pair[0] != pair[1]
  end

  private_class_method :pair_nucleotides, :count_mutations,
    :mutation_detected?
end
