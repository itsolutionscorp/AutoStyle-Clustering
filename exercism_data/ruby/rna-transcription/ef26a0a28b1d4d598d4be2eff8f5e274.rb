class DNA
  DNA_NUCLEOTIDES = ["A", "T", "C", "G"]
  RNA_NUCLEOTIDES = ["A", "U", "C", "G"]
  NUCLEOTIDES = (DNA_NUCLEOTIDES + RNA_NUCLEOTIDES).uniq

  def initialize(string)
    @string = string
  end

  def count(nucleotide)
    unless valid_nucleotide?(nucleotide)
      raise ArgumentError.new("Unknown nucleotide #{nucleotide.inspect}. Expected on of #{NUCLEOTIDES.inspect}")
    end
    @string.count(nucleotide)
  end

  def nucleotide_counts
    Hash[DNA_NUCLEOTIDES.collect { |nucleotide|
      [nucleotide, count(nucleotide)]
    }]
  end

  private

  def valid_nucleotide?(nucleotide)
    NUCLEOTIDES.include?(nucleotide)
  end
end
