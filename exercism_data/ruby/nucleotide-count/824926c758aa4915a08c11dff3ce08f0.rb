class DNA

  def initialize(dna)
    if dna.match(/[^ACTG]/)
      raise ArgumentError
    else
      @dna = dna
      @nucl = %w[A C T G U]
    end
  end

  def count(nucleotide)
    if @nucl.include?(nucleotide)
      @dna.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    nucleotides.each { |key, value| nucleotides[key] = count(key) }
  end

end
