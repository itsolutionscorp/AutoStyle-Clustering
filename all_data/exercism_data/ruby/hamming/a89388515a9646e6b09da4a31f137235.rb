class Hamming

  # return hamming distance between 2 strands
  def self.compute(strand_1, strand_2)
    raise Exception.new("Strands are not the same length") if strand_1.size != strand_2.size
    distance = 0
    chars_array_1 = strand_1.chars
    chars_array_2 = strand_2.chars
    chars_array_1.each_index do |i|
      distance += 1 if chars_array_1[i] != chars_array_2[i]
    end
    distance
  end

end
