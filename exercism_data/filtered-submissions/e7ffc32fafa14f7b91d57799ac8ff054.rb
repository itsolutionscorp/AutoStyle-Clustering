module Hamming
  def Hamming.compute(left_strand, right_strand)
    shorter, longer = [left_strand, right_strand].sort_by &:length
    shorter.chars.zip(longer.chars).count {|a, b| a != b }
  end
end
