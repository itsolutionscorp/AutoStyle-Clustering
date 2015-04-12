module Hamming
  def compute(left_strand, right_strand)
    (0..left_strand.length).count do |i|
      left_strand[i] != right_strand[i]
    end
  end
end
