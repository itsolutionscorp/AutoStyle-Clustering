class Hamming

  def self.compute(strand1, strand2)
    sequence = sequence(strand1, strand2)
    non_mutations = pull_nucleotide_matches(sequence)
    non_mutations.count
  end

  def self.sequence(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    sequence = strand1.zip(strand2)
  end

  def self.pull_nucleotide_matches(sequence)
    sequence.select{|strand| valid_pair?(strand) && !mutation?(strand)}
  end

  def self.mutation?(strand)
    strand.uniq != strand
  end

  def self.valid_pair?(strand)
    !strand.include?(nil)
  end
end
