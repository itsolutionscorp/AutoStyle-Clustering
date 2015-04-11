class DNA
  NUCLEOTIDES = [
    ADENINE = "A",
    CYTOSINE = "C",
    GUANINE = "G",
    THYMINE = "T",
  ]

  def initialize(nucleotides)
    raise ArgumentError, "`#{nucleotides}' contains invalid DNA nucleotides" unless nucleotides =~ /\A[#{NUCLEOTIDES.join}]*\z/
    @nucleotides = nucleotides
  end

  def hamming_distance(nucleotides)
    differences( nucleotides).size()
  end

  private

  def differences(other_nucleotides)
    @nucleotides.chars.zip(other_nucleotides.chars).select do |a, b|
      # `zip' automatically pads either array with nils if they're not of equal length,
      # so we need to account for that by not selecting values where the other array contains nil values
      a != b && !b.nil?
    end
  end
end
