class Hamming
  def compute(strand, other_strand)
    strand.each_char.each_with_index.count do |point, i|
      other_point = other_strand[i]
      other_point && point != other_point
    end
  end
end
