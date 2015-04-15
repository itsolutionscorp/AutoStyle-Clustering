class DNA

  attr_reader :sequence

  def initialize(sequence)
   @sequence = sequence
   raise ArgumentError unless validated_dna
  end

  def count(nucleotide)
   raise ArgumentError unless nucleotides.include?(nucleotide)
   sequence.count(nucleotide)
  end

  def nucleotide_counts
   {'A'=>count('A'), 'T'=>count('T'), 'C'=>count('C'), 'G'=>count('G')}
  end

  def validated_dna
   sequence.chars.all? {|char| dna_nucleotides.include?(char)}
  end

  def dna_nucleotides
    ['A','T','C','G']
  end

  def nucleotides
    ['A','T','C','G','U']
  end
end
