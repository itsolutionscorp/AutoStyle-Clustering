class DNA

  def initialize(dna)
    @dna = dna
  end

  def count(nucleotide)
    raise ArgumentError unless all_nucleotides.include?(nucleotide)
    my_nucleotides.select {|nuc| nuc == nucleotide}.count
  end

  def nucleotide_counts
    dna_nucleotides.inject({}) {|counts,nuc| counts[nuc] = count(nuc); counts }
  end

  private

  def my_nucleotides
    @dna.chars
  end

  def dna_nucleotides
    %w{ A T C G }
  end

  def all_nucleotides
    dna_nucleotides + ['U']
  end

end
