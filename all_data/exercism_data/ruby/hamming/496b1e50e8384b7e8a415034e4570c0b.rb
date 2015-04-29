# jillalynch solution to exercism.io Hamming exercise
# calculates the Hamming difference between two DNA strands
class Hamming
  def self.compute(strand_x, strand_y)
    hamming_distance = 0
    # min = str1.length < str2.length ? str1.length : str2.length
    (0...[strand_x.length, strand_y.length].min).each do |index|
      hamming_distance += 1 unless char_compare(strand_x[index], strand_y[index])
    end
    hamming_distance
  end

  def self.char_compare(base_x, base_y)
    base_x.eql?(base_y)
  end
end
