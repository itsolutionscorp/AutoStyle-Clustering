class DNA
  NUCLEOTIDES = [
    ADENINE = "A",
    CYTOSINE = "C",
    GUANINE = "G",
    THYMINE = "T",
  ]

  def initialize(strand)
    validate_strand(strand)
    @strand = strand
  end

  def hamming_distance(strand)
    differences(strand).size()
  end

  private

  def validate_strand(strand)
    unless strand =~ /\A[#{NUCLEOTIDES.join}]*\z/
      raise ArgumentError, "`#{strand}' contains invalid DNA nucleotides"
    end
  end

  def differences(other_strand)
    sliced_strand = @strand.chars.slice(0, other_strand.length)

    sliced_strand.zip(other_strand.chars).select do |a, b|
      a != b
    end
  end
end
