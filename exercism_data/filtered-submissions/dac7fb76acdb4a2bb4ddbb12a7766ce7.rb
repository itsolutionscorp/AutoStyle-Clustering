class Hamming
  def compute(strand1, strand2)
    hamming_distance = 0
    strand_length = [strand1.length, strand2.length].min
    (0...strand_length).each do |point|
      hamming_distance += 1 if strand1[point] != strand2[point]
    end
    hamming_distance
  end
end
