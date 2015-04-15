class DNA

  def initialize(dna)
    raise ArgumentError if dna.chars.any? {|c| !nucleotides.include?(c) }
    @dna = dna
  end

  def self.nucleotides; %w(A T C G); end

  def count(nucleotide)
    nucleotide_counts[nucleotide] || raise(ArgumentError)
  end

  def nucleotide_counts
    null_dna.merge(
      @dna.chars.group_by {|a|a}.map {|k, v|
        Hash[k, v.count]
      }.reduce({}, &:merge)
    )
  end

  private

  def nucleotides
    self.class.nucleotides
  end

  def null_dna
    Hash[nucleotides.zip [0]*nucleotides.size]
  end
end
