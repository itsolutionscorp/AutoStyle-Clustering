class Hamming

  def self.compute strand_1, strand_2
    distance = 0
    strand_length = strand_1.length
    strand_length.times do |i|
      distance += strand_1[i] == strand_2[i] ? 0 : 1
    end
    distance
  end

end
