class DNA
  NUCLEOTIDES = ['A', 'C', 'G', 'T', 'U']
  DNA = ['A', 'C', 'G', 'T']

  def initialize(dna_string)
    raise ArgumentError unless dna?(dna_string)
    @dna_string = dna_string
    @nucleotide_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide?(nucleotide)
    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    @nucleotide_counts.each_key { |nucleotide| @nucleotide_counts[nucleotide] = count(nucleotide)}
  end

  private
    def nucleotide?(nucleotide)
      NUCLEOTIDES.include?(nucleotide)
    end

    def dna?(dna_string)
      dna_string.chars.all? { |char| DNA.include?(char) }
    end
end
