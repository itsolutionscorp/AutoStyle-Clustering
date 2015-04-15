class Hamming

  def self.compute(strand_one, strand_two)
    strand_one = strand_one.split(//)
    strand_two = strand_two.split(//)
    hamming_distance = 0

    strand_one.each_with_index do |symbol, i|
      hamming_distance += 1 if symbol != strand_two[i]
    end

    hamming_distance
  end

end
