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

class DNA

  def initialize(sequence)
    sequence = sequence.chars.map {|i| Nucleotide.new(i) }
    raise ArgumentError if sequence.find {|i| not i.dna? }

    @sequence = sequence
  end

  def count(nucleotide)
    nucleotide = Nucleotide.new(nucleotide)
    raise ArgumentError unless nucleotide.valid?

    @sequence.select {|i| i == nucleotide }.size
  end

  def nucleotide_counts
    Nucleotide::DNA.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
end
