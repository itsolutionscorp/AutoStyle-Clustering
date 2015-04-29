class Nucleotide
  attr_reader :histogram

  Adenine  = 'A'
  Cytosine = 'C'
  Guanine  = 'G'
  Thymine  = 'T'
  Nucleotides = [Adenine, Cytosine, Guanine, Thymine]

  def self.from_dna sequence
    seq = sequence.chars
    fail ArgumentError.new if seq.detect { |s| !Nucleotides.include? s }
    Nucleotide.new seq
  end

  def initialize sequence
    @dna = sequence
    @histogram = Hash[*Nucleotides.map { |n| [n, @dna.count(n)]}.flatten]
  end

  def count nucleotide
    @histogram[nucleotide]
  end

end
