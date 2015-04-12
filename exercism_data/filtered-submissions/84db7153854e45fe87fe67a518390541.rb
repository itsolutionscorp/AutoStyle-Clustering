
# @param [Array] first_strand
# @param [Array] second_strand


module Hamming
  # @param [String] first_strand
  # @param [String] second_strand
  def compute (first_strand, second_strand)

    l = [first_strand.length, second_strand.length].min
    hamming_distance = 0
    (0..l).each do |i|
      hamming_distance+=1 if first_strand[i] != second_strand[i]
    end
    hamming_distance
  end
end
