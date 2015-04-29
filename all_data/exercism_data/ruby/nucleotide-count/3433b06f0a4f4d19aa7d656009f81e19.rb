class DNA

  def initialize(dna_in)
    @dna_nuces = [ 'A', 'C', 'G', 'T' ]
    @rna_nuces = [ 'A', 'C', 'G', 'U' ]
    @all_nuces = @dna_nuces | @rna_nuces
    dna_in.chars { |i| raise ArgumentError if @dna_nuces.count(i) == 0 }
    @dna_in = dna_in
  end
  
  def count(nuc)
    raise ArgumentError if @all_nuces.count(nuc) == 0
    @dna_in.count(nuc)
  end
  
  def nucleotide_counts
    @dna_nuces.each_with_object({}) { |n, c| c[n] = @dna_in.count(n) }
  end

end
