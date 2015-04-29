class Hamming
  def self.compute(a_strand, b_strand)
    pair_window = a_strand.chars.zip(b_strand.chars).take(b_strand.length)
    pair_window.count { |a, b| a != b }
  end
end
