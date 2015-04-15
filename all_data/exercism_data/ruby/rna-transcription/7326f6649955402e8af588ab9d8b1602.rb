class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.chars
  end

  def to_rna
    strand.map { |n| replace(n) }.join
  end

  def replacements
    {'T' => 'U'}
  end

  def replace(nucleotide)
    replacements[nucleotide] || nucleotide
  end
end
