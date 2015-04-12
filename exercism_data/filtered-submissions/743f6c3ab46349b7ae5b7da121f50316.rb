class Hamming
  def compute left_strand, right_strand
    hamming_distance = 0
    left_strand.chars.each_with_index do |left_strand_char, i|
      if i < right_strand.length and left_strand_char != right_strand[i]
        hamming_distance += 1
      end
    end
    hamming_distance
  end
end
