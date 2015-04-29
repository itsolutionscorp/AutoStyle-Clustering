class Hamming
  def self.compute(strand_1, strand_2)
    new(strand_1, strand_2).execute
  end

  def initialize(strand_1, strand_2)
    @strand_1 = strand_1
    @strand_2 = strand_2
  end

  def execute
    strand_pairs.reduce(0) do |hamming_distance, (nucleotide_1, nucleotide_2)|
      hamming_distance += nucleotide_distance(nucleotide_1, nucleotide_2)
    end
  end

  private

  attr_reader :strand_1, :strand_2

  def nucleotide_distance(nucleotide_1, nucleotide_2)
    if nucleotide_1 != nucleotide_2
      1
    else
      0
    end
  end

  def strand_pairs
    strand_1_arr.zip(strand_2_arr)
  end

  def strand_1_arr
    strand_1.split("")
  end

  def strand_2_arr
    strand_2.split("")
  end
end
