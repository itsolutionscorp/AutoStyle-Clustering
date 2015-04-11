class DNA
  DNA_TYPES = %w(A T C G)
  RNA_TYPES = %w(A C G U)

  def initialize(str)
    nucleotides  = str.chars
    raise ArgumentError unless nucleotides.all? { |n| valid_dna?(n) }
    @nucleotides = nucleotides
  end

  def count(n)
    raise ArgumentError unless valid_dna?(n) || valid_rna?(n)
    nucleotide_counts[n] || 0
  end

  def nucleotide_counts
    counts = {}
    DNA_TYPES.each    { |n| counts[n] = 0 }
    @nucleotides.each { |n| counts[n] += 1 }
    counts
  end

  private

  def valid_dna?(n)
    DNA_TYPES.include?(n)
  end

  def valid_rna?(n)
    RNA_TYPES.include?(n)
  end
end
