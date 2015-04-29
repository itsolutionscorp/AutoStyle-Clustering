class Hamming

  def self.compute(strand_1_s, strand_2_s)
    strand_1_s ||= ''
    strand_2_s ||= ''

    short_strand, long_strand = (strand_1_s.length <= strand_2_s.length) ?
      [strand_1_s, strand_2_s] :
      [strand_2_s, strand_1_s]
    paired_strands = short_strand.chars.to_a.zip(long_strand.chars.to_a)

    paired_strands.count{|a, b| a!= b }
  end
end
