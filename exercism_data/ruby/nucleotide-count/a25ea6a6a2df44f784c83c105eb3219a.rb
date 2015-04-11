class DNA

  def initialize(dna_in)
    @dna_nuces = [ 'A', 'C', 'G', 'T' ]
    @rna_nuces = [ 'A', 'C', 'G', 'U' ]
    @nuces = @dna_nuces | @rna_nuces
    for i in dna_in.chars
      if @dna_nuces.count(i) == 0
        raise ArgumentError
      end
    end
    @dna_in = dna_in
  end
  
  def count(nuc)
    if @nuces.count(nuc) == 0
      raise ArgumentError
    end
    return @dna_in.count(nuc)
  end
  
  def nucleotide_counts
    c = {}
    for i in @dna_nuces 
      c[i] = @dna_in.count(i)
    end
    return c
  end

end
