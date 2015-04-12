module Hamming
  def Hamming.compute(left_strand, right_strand)
    shorter, longer = [left_strand, right_strand].sort_by &:length
    shorter.chars.zip(longer.chars).inject 0 do |running_sum, char_pair|
      running_sum + (char_pair.inject(:==) ? 0 : 1)
    end
  end
end
