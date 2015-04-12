class Hamming

  def compute(strand_one, strand_two)
    strand_one = strand_one.chars
    hamming_distance = 0

    strand_one.each_with_index do |symbol, i|
      hamming_distance += 1 if symbol != strand_two[i]
    end

    hamming_distance
  end

end
