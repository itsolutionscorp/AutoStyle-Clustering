class DNA

  attr_reader :strand

  def initialize(dna)
    @strand = dna.split('')
  end

  def count(nucleotide)
    if recognized_nucleotide.include?(nucleotide)
      strand.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    values = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    strand.each do |nucleotide|
      values[nucleotide] = count(nucleotide)
    end
    values
  end

  def recognized_nucleotide
    %w(A T C G U)
  end

end
