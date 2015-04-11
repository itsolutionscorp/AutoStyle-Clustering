class DNA < Struct.new(:sequence)
  def count(nucleotide)
    raise ArgumentError unless %w(A T C G U).include?(nucleotide)
    sequence.chars.count{ |n| n == nucleotide }
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G'),
    }
  end
end
