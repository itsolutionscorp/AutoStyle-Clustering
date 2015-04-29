class Nucleotide < Struct.new(:char)
  DNA = %w(A C G T)
  RNA = %w(A C G U)

  def valid?
    dna? or rna?
  end

  def dna?
    DNA.include? char
  end

  def rna?
    RNA.include? char
  end
end

module DNAAdapter
  def initialize(sequence)
    super(sequence.chars.map{|char| Nucleotide.new(char) })
  end

  def count(nucleotide)
    super(Nucleotide.new(nucleotide))
  end
end

class DNA
  prepend DNAAdapter

  def initialize(sequence)
    raise ArgumentError unless sequence.all? {|nucleotide| nucleotide.dna? }
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide.valid?
    @sequence.count {|i| i == nucleotide }
  end

  def nucleotide_counts
    Nucleotide::DNA.each_with_object({}) do |nucleotide, nucleotide_count|
      nucleotide_count[nucleotide] = count(nucleotide)
    end
  end
end
