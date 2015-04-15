class DNA
  NUCLEOTIDES = ['A', 'C', 'G', 'T', 'U']
  DNA = ['A', 'C', 'G', 'T']

  attr_reader :strand, :nucleotides

  def initialize(strand)
    raise ArgumentError unless dna?(strand)
    @strand = strand
    @nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide?(nucleotide)
    strand.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.each_key { |nucleotide| nucleotides[nucleotide] = count(nucleotide)}
  end

  private
    def nucleotide?(nucleotide)
      NUCLEOTIDES.include?(nucleotide)
    end

    def dna?(strand)
      strand.chars.all? { |char| DNA.include?(char) }
    end
end
