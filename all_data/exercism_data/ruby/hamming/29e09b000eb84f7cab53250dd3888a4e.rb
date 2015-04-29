class Hamming

  def self.compute(strand_1, strand_2)
    Hamming.new(strand_1, strand_2).distance
  end

  attr_reader :strand_1, :strand_2

  def initialize(strand_1, strand_2)
    @strand_1 = strand_1.chars
    @strand_2 = strand_2.chars
  end

  def distance
    paired_strands(strand_1, strand_2).count do |nucleotide_1, nucleotide_2|
      mutation?(nucleotide_1, nucleotide_2)
    end
  end

  def paired_strands(strand_1, strand_2)
    strand_1.zip(strand_2)
  end

  def mutation?(nucleotide_1, nucleotide_2)
    !(nucleotide_1.nil? || nucleotide_2.nil?) && (nucleotide_1 != nucleotide_2)
  end
end
