class Hamming

  def self.compute(strand, strand2)
    distance = 0
    compare_strand = strand.chars.first(strand2.size)
    compare_strand.each_with_index do |char, index|
      distance += 1 unless char == strand2[index]
    end
    distance
  end

end
