class DNA
  NUCLEOTIDES = %w[A T C G]
  EMPTY = Hash[NUCLEOTIDES.map { |n| [n, 0] }].freeze

  def initialize(dna)
    @count = EMPTY.dup
    dna.each_char do |nucleotide|
      @count[validate(nucleotide)] += 1
    end
  end

  def count(nucleotide)
    # uracil is allowed as a special case for compatibility with RNA
    return 0 if nucleotide == 'U'
    @count[validate(nucleotide)]
  end

  def nucleotide_counts
    @count
  end

  private

  def validate(nucleotide)
    unless NUCLEOTIDES.include? nucleotide
      raise ArgumentError, "Invalid nucleotide: #{nucleotide}"
    end
    nucleotide
  end
end
