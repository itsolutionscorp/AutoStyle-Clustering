class DNA

  attr_reader :nucleotides

  def initialize(strand)
    raise ArgumentError unless valid_dna?(strand)
    @nucleotides = strand
  end

  def valid_dna?(input)
    letters = input.split("")
    letters.all?{|letter| dna_nucleotides.include?(letter)}
  end

  def count(nucleotide)
    raise ArgumentError.new("Bad DNA") unless valid_dna?(nucleotide)
    nucleotides.count(nucleotide)
  end

  def dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def nucleotide_counts
    {'A' => count('A'), 'T' => count('T'), 'G' => count('G'), 'C' => count('C')}
  end

end
