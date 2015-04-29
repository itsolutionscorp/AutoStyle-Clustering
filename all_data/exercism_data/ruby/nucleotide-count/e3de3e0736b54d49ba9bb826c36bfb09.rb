class DNA

  def initialize(dna_string)
    DNA.dna_string?(dna_string) or fail ArgumentError
    @dna_string = dna_string
  end

  def count(nucleotide)
    DNA.nucleotide?(nucleotide) or fail ArgumentError
    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.each_with_object(DNA.tally) do |nucleotide, tally|
      tally[nucleotide] += 1
    end
  end

  private

  DNA_NUCLEOTIDES = %w(A T C G)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + %w(U)

  def self.dna_string?(s)
    s.chars.all? { |c| dna_nucleotide?(c) }
  end

  def self.dna_nucleotide?(c)
    DNA_NUCLEOTIDES.include?(c)
  end

  def self.nucleotide?(nucleotide)
    ALL_NUCLEOTIDES.include?(nucleotide)
  end

  def self.tally
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, tally|
      tally[nucleotide] = 0
    end
  end

  def nucleotides
    @dna_string.chars
  end

end
