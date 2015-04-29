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
    raise ArgumentError if sequence.find {|i| not i.dna? }
    @sequence = sequence
  end

  def count(nucleotide)
    raise ArgumentError unless nucleotide.valid?
    @sequence.select {|i| i == nucleotide }.size
  end

  def nucleotide_counts
    Nucleotide::DNA.each_with_object({}) do |nucleotide, nucleotide_counts|
      nucleotide_counts[nucleotide] = count(nucleotide)
    end
  end
end
