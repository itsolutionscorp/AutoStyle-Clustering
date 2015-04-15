class DNA
  def initialize(dna)
    nucleotides = dna.chars
    @counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    raise ArgumentError if (nucleotides & @counts.keys) != nucleotides.uniq
    @counts.keys.each { |nt|
      @counts[nt] = nucleotides.count(nt)
    }
  end

  def count(nucleotide)
    raise ArgumentError if !@counts.has_key?(nucleotide)
    @counts[nucleotide]
  end

  def nucleotide_counts
    @counts
  end
end 
