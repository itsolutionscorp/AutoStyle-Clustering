class Hamming

  def self.compute(dna1, dna2)
    @length = [dna1.length, dna2.length].min
    count_the_distance(dna1, dna2)
  end

private
  def self.count_the_distance(dna1, dna2)
    distance = 0
    @length.times do |i|
      distance += 1 unless dna1[i] == dna2[i]
    end

    distance
  end

end
