class DNA

  def initialize(dna_sequence)
    validate(dna_sequence)
    @input = dna_sequence
  end

  def validate(dna_sequence)
    unless valid_nucleotides? dna_sequence
      raise ArgumentError, "#{dna_sequence} is an invalid DNA sequence."
    end
    if rna? dna_sequence
      raise ArgumentError, "#{dna_sequence} is an RNA sequence."
    end
  end

  def count(dna_sequence)
    unless nucleotides.include? dna_sequence
      raise ArgumentError, "No such nucleotide."
    end
    @input.count(dna_sequence)
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'C' => count('C'),
      'T' => count('T'),
      'G' => count('G'),
    }
  end

  private

  def rna?(dna_sequence)
    valid_rna_nucleotides?(dna_sequence) && dna_sequence.include?('U')
  end

  def valid_nucleotides?(dna_sequence)
    !dna_sequence.chars.find { |nucleotide| !nucleotides.include? nucleotide }
  end

  def valid_rna_nucleotides?(dna_sequence)
    !dna_sequence.chars.find { |nucleotide| !rna_nucleotides.include? nucleotide }
  end

  def nucleotides
    rna_nucleotides + ['T']
  end

  def rna_nucleotides
    ['A','U','C','G']
  end

end
