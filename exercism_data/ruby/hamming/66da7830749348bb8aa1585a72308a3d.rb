class Hamming
  def self.compute(strand1, strand2)
    hamming = new(strand1, strand2)
    hamming.distance
  end

  def initialize(strand1, strand2)
    @strand1, @strand2 = normalize([strand1, strand2])
  end

  def normalize(strands)
    strand_length = strands.map(&:size).min
    strands.map { |strand| strand[0...strand_length] }
  end

  def distance
    distance = 0
    @strand1.chars.each_with_index do |char, index|
      distance += 1 unless char == @strand2[index]
    end

    distance
  end
end
