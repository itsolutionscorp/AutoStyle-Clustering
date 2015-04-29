class Hamming
  def self.compute(strand, other_strand)
    distance = 0

    strand.each_char.each_with_index do |point, i|
      other_point = other_strand[i]
      distance += 1 if other_point && point != other_point
    end

    distance
  end
end
