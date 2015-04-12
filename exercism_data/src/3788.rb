class Hamming

  def compute(strand1, strand2)
    min_len = [strand1.length, strand2.length].min
    hamming_distance = 0

    (0...min_len).each do |i|
      hamming_distance += 1 if strand1[i] != strand2[i]
    end

    hamming_distance
  end

end
