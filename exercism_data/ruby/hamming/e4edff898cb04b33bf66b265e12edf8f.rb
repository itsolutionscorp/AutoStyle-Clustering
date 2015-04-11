class Hamming
  def self.compute(strand_one, strand_two)
    new(strand_one, strand_two).compute
  end

  def initialize(strand_one, strand_two)
    @strand_one = strand_one.chars
    @strand_two = strand_two.chars
  end

  def compute
    sum_by do |nucleotide_one, nucleotide_two|
      distance(nucleotide_one, nucleotide_two)
    end
  end

  def sum_by
    aligned_strands.inject(0) do |total, (nucleotide_one, nucleotide_two)|
      total + yield(nucleotide_one, nucleotide_two)
    end
  end

  def aligned_strands
    @strand_one.zip(@strand_two)
  end

  def distance(nucleotide_one, nucleotide_two)
    nucleotide_one == nucleotide_two ? 0 : 1
  end
end
