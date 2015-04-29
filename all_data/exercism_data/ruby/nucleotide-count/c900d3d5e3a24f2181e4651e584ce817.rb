class DNA
  DNA_TYPES = %w(A C T G)
  RNA_TYPES = %w(A C G U)

  def initialize(input)
    dna  = input.chars
    dna.each { |n| raise ArgumentError unless dna_type?(n) }
    @dna = dna
  end

  def nucleotide_counts
    {}.tap do |counts|
      DNA_TYPES.each { |n| counts[n] = 0 }
      @dna.each      { |n| counts[n] += 1 }
    end
  end

  def count(n)
    raise ArgumentError unless dna_type?(n) || rna_type?(n)
    nucleotide_counts[n] || 0
  end

  private

  def dna_type?(n)
    DNA_TYPES.include?(n)
  end

  def rna_type?(n)
    RNA_TYPES.include?(n)
  end
end
