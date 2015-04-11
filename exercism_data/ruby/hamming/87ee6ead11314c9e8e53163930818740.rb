require 'pry'
class Hamming
  def self.compute(strand_1, strand_2)
    @strand_1, @strand_2 = strand_1, strand_2
    hamming_count
  end

  private

  def self.hamming_count
    total = 0
    min_length.times do |i|
      total +=1 unless equal_nucleotides?(i)
    end
    total
  end

  def self.equal_nucleotides?(index)
    @strand_1[index] == @strand_2[index]
  end

  def self.min_length
    [@strand_1.length, @strand_2.length].min
  end
end
