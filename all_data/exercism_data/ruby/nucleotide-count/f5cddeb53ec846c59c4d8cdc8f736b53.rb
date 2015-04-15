class DNA

  NUCLEOTIDES = %w(A C G T U)
  DNA = %w(A C G T)

  def initialize(dna)
    @dna = dna.to_s.upcase.chars
    @dna.map do |char|
      unless DNA.include?(char)
        raise ArgumentError
      end
    end
  end

  def count(nucleotide)
    if not NUCLEOTIDES.include?(nucleotide)
      raise ArgumentError
    elsif DNA.include?(nucleotide)
      nucleotide_counts[nucleotide]
    else 0 end
  end

  def nucleotide_counts
    hash = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @dna.map do |char|
      hash[char] += 1
    end
    hash
  end
end
