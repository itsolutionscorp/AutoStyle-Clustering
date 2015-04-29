class DNA
  def initialize(sequence)
    raise ArgumentError unless sequence =~ /\A[ACTG]*\Z/

    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless ['A', 'C', 'T', 'G', 'U'].include?(nucleotide)

    @sequence.chars.select {|i| i == nucleotide }.size
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G')
    }
  end
end
