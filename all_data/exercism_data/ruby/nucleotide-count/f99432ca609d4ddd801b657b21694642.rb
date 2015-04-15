class DNA

  DNA_NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + %w(U)

  def initialize(dna_string)
    DNA.dna_string?(dna_string) or fail ArgumentError
    @dna_string = dna_string
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) do |nucleotide, tally|
      tally[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    DNA.nucleotide?(nucleotide) or fail ArgumentError
    @dna_string.count(nucleotide)
  end

  private

  def self.dna_string?(s)
    s.chars.all? { |c| dna_nucleotide?(c) }
  end

  def self.dna_nucleotide?(x)
    DNA_NUCLEOTIDES.include?(x)
  end

  def self.nucleotide?(x)
    ALL_NUCLEOTIDES.include?(x)
  end

end
