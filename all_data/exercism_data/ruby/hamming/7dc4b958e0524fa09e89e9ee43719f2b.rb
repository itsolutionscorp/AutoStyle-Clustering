require 'pry'
class Hamming

  def initialize(dna_1, dna_2)
    @difference = 0
    @dna_1 = dna_1
    @dna_2 = dna_2
  end

  def self.compute(dna_1, dna_2)
    dna_1_sequence = dna_1.split('')
    dna_2_sequence = dna_2.split('')

    hamming = new(dna_1_sequence, dna_2_sequence)
    hamming.compare_dna
  end

  def compare_dna
    size = [@dna_1.size, @dna_2.size].min
    0.upto(size - 1) { |index| @difference += 1 unless @dna_1[index] == @dna_2[index] }
    @difference
  end

end
