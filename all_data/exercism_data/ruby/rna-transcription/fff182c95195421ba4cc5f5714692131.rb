class Complement
  def self.of_dna(sequence)
    find_complement(sequence, rna_sequence, dna_sequence)
  end

  def self.of_rna(sequence)
    find_complement(sequence, dna_sequence, rna_sequence)
  end

  def self.find_complement(sequence, seqA, seqB)
    sequence.chars.inject("") do |complement, elem|
      complement += seqA[seqB.index(elem)]
    end
  end

  private
  def self.dna_sequence
    %w[G C T A]
  end

  def self.rna_sequence
    %w[C G A U]
  end
end
