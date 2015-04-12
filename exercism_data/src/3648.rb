module Hamming
  def compute(a_strand, b_strand)
    pair_window = a_strand.chars.zip(b_strand.chars).take_while { |a,b| !(a.nil? or b.nil?) }
    pair_window.count { |a, b| a != b }
  end
end
