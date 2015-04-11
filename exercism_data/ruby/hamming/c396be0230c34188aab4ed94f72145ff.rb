class Hamming
  def self.compute(dna_1, dna_2)
    HammingDistanceCalculator.new(dna_1, dna_2).calculate
  end
end

class HammingDistanceCalculator
  attr_reader :dna_1, :dna_2

  def initialize(dna_1, dna_2)
    @dna_1 = dna_1
    @dna_2 = dna_2
  end

  def calculate
    compare_length.inject(0) do |distance, index|
      difference_on?(index) ? distance + 1 : distance
    end
  end

  private

  def compare_length
    0...min_length_of_dna
  end

  def min_length_of_dna
    [dna_1, dna_2].map(&:length).min
  end

  def difference_on?(index)
    dna_1[index] != dna_2[index]
  end
end
