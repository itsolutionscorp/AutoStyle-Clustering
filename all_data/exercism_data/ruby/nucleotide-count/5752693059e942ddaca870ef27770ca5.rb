class RNA
  NUCLEOTIDES = [
    ADENINE = "A",
    CYTOSINE = "C",
    GUANINE = "G",
    URACIL = "U",
  ]

  def self.valid_nucleotide?(nucleotide)
    NUCLEOTIDES.include?(nucleotide)
  end
end

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

  def count(nucleotide)
    return @nucleotides.count(nucleotide) if self.class.valid_nucleotide?(nucleotide)

    return 0 if RNA.valid_nucleotide?(nucleotide)

    raise ArgumentError, "`#{nucleotide}' is not a valid nucleotide"
  end

  def nucleotide_counts
    {
      ADENINE => count(ADENINE),
      CYTOSINE => count(CYTOSINE),
      GUANINE => count(GUANINE),
      THYMINE => count(THYMINE)
    }
  end

  def self.valid_nucleotide?(nucleotide)
    NUCLEOTIDES.include?(nucleotide)
  end
end
