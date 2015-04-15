class DNA

  def initialize(dna)
    if dna.match(/[^ACTG]/)
      raise ArgumentError
    else
      @dna = dna
      @nucl = %w[A C T G U]
      @dna_nucl = @nucl - %w[U]
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
    @dna_nucl.each_with_object({}) { |key, hash| hash[key] = count(key) }
  end

end
