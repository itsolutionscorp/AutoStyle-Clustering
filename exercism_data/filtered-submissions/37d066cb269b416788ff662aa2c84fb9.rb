class Hamming

  def compute strand_1, strand_2
    hamming_distance = 0

    if strand_1.size > strand_2.size
      strand_1 = strand_1[0...strand_2.size]
    else
      strand_2 = strand_2[0...strand_1.size]
    end

    strand_1.chars.zip(strand_2.chars).each do |strand_1_char, strand_2_char|
      hamming_distance += 1 if strand_1_char != strand_2_char
    end

    hamming_distance
  end
end
