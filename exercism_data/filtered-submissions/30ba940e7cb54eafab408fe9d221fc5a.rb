class Hamming
  def Hamming.compute(strand1, strand2)
    strand_ary_1 = strand1.chars
    strand_ary_2 = strand2.chars
    min_strand_length = [strand_ary_1.length, strand_ary_2.length].min
    hamming_distance = 0
    strand_ary_1.each_with_index do |c, i|
      break if i >= min_strand_length
      hamming_distance += 1 if c != strand_ary_2[i]
    end
    hamming_distance
  end
end
