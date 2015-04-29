module Hamming

  module_function

  def compute(first_strand, second_strand)
    length = [first_strand, second_strand].map(&:size).min
    pairs = first_strand.each_char.zip(second_strand.each_char).take(length)

    pairs.count { |a, b| a != b }
  end
end
