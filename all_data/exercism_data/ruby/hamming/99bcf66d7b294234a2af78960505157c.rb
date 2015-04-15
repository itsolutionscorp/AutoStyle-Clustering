# jillalynch solution to exercism.io Hamming exercise
# calculates the Hamming difference between two DNA strands
class Hamming
  def self.compute(strand_x, strand_y)
    hamming_distance = 0
    shorter_strand_length = shorter_strand(strand_x, strand_y)
	
    shorter_strand_length.times do |index|
      hamming_distance += 1 unless base_equal?(strand_x[index], strand_y[index])
    end
    
    hamming_distance
  end

  def self.base_equal?(base_x, base_y)
    base_x.eql?(base_y)
  end

  def self.shorter_strand(strand_a, strand_b)
    [strand_a.length, strand_b.length].min
  end
end
