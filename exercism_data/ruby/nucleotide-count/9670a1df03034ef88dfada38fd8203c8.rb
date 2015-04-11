class DNA

  def initialize(sequence)
    @sequence = sequence
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'C' => count('C'),
      'G' => count('G'),
      'T' => count('T')
    }
  end

  def count(nucleotide_code)
    raise ArgumentError unless %w( A C G T U ).include?(nucleotide_code)
    @sequence.chars.count{|c| c == nucleotide_code }
  end
end
