class DNA
  NUCLEOTIDES = ['A', 'C', 'G', 'T', 'U']
  DNA = ['A', 'C', 'G', 'T']

  attr_reader :dna_string, :nucleotides

  def initialize(dna_string)
    raise ArgumentError unless dna?(dna_string)
    @dna_string = dna_string
    @nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide?(nucleotide)
    dna_string.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.each_key { |nucleotide| nucleotides[nucleotide] = count(nucleotide)}
  end

  private
    def nucleotide?(nucleotide)
      NUCLEOTIDES.include?(nucleotide)
    end

    def dna?(dna_string)
      dna_string.chars.all? { |char| DNA.include?(char) }
    end
end
