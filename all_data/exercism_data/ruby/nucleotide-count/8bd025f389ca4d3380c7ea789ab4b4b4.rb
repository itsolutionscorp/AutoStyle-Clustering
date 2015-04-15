class DNA

  NUCLEOTIDES = ["A", "C", "G", "T", "U"]
  DNA_NUCLEOTIDES = ["A", "C", "G", "T"]

  def initialize(dna)
    raise ArgumentError.new("This is not DNA") unless has_dna?(dna)
    @dna = dna
  end

  def count(letter)
    raise ArgumentError.new("This is not a nucleotide") unless is_nucleotide?(letter)
    @dna.chars.count(letter)
  end

  def nucleotide_counts
    counts = Hash.new
    DNA_NUCLEOTIDES.each do |nucleotide|
      counts[nucleotide] = @dna.chars.count(nucleotide)
    end
    counts
  end

  private

  def has_dna?(dna)
    dna.match(/^[ACGT]*$/)
  end

  def is_nucleotide?(letter)
    NUCLEOTIDES.include?(letter)
  end
end
