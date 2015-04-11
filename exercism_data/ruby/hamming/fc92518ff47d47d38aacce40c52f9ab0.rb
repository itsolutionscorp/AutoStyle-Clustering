class Hamming

  def self.compute(first_strand, second_strand)
    zipped_strands = first_strand.chars.zip(second_strand.chars)
    zipped_strands.count { |strand| strand[0] != strand[1] unless strand[0].nil? || strand[1].nil? }
  end
end
