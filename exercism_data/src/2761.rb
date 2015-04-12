module Hamming
  module_function

  # Compute the Hamming distance between two DNA strings
  # @param left_strand [String] One or more DNA bases
  # @param right_strand [String] One or more DNA bases
  # @return [Integer] number of differences between strings
  # @see http://rosalind.info/problems/hamm/
  def compute(left_strand,right_strand)
    return 0 if left_strand == right_strand

    left_chars = left_strand.chars
    right_chars = right_strand.chars

    distance = 0

    left_chars.each_with_index do |left_char,idx|
      right_char = right_chars[idx]
      break if right_char.nil? # ignore extra length on first strand when longer

      distance += 1 unless left_char == right_char
    end

    distance
  end
end
