class DNA

  def initialize(dna)
    @dna = dna.split('')
  end

  def count(nucleotide)
    if @dna == [] || nucleotide == "U"
      0
    elsif @dna.include?(nucleotide)
      nucleotide_counts[nucleotide]
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    values = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @dna.each do |nucleotide|
      values[nucleotide] += 1
    end
    values
  end

end
