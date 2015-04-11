class Hamming
  def initialize(strand1, strand2)
    @strand_1_array = strand1.split('')
    @strand_2_array = strand2.split('')
    @distance = 0
    find_distance
  end

  def self.compute(strand1, strand2)
    new(strand1, strand2).distance
  end

  def find_distance
    @strand_1_array.each_with_index do |nucleotide, index|
      @distance += 1 if @strand_2_array[index] != nucleotide
    end
  end

  def distance
    @distance
  end
end
