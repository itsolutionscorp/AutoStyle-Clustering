
# @param [Array] first_strand
# @param [Array] second_strand


module Hamming
  # @param [String] first_strand
  # @param [String] second_strand
  def self.compute (first_strand, second_strand)

    length = [first_strand.length, second_strand.length].min
    length.times.count {|i| first_strand[i] != second_strand[i]}
  end
end


