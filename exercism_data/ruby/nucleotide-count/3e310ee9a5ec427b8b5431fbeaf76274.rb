class DNA

  def initialize(sequence)
    validate(sequence)
    @input = sequence
  end

  def validate(sequence)
    unless valid_nucleotides? sequence
      raise ArgumentError, "#{sequence} is an invalid DNA sequence."
    end
    if rna? sequence
      raise ArgumentError, "#{sequence} is an RNA sequence."
    end
  end

  def count(sequence)
    unless nucleotides.include? sequence
      raise ArgumentError, "No such nucleotide."
    end
    @input.count(sequence)
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

  def rna?(sequence)
    valid_rna_nucleotides?(sequence) && sequence.include?('U')
  end

  def valid_nucleotides?(sequence)
    !sequence.chars.find { |nucleotide| !nucleotides.include? nucleotide }
  end

  def valid_rna_nucleotides?(sequence)
    !sequence.chars.find { |nucleotide| !rna_nucleotides.include? nucleotide }
  end

  def nucleotides
    rna_nucleotides + ['T']
  end

  def rna_nucleotides
    ['A','U','C','G']
  end

end
