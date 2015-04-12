def compute(strand_1_s, strand_2_s)
    strand_1_s ||= ''
    strand_2_s ||= ''

    short_strand, long_strand = [strand_1_s, strand_2_s].sort_by!(&:length)
    paired_strands = short_strand.chars.zip(long_strand.chars)

    paired_strands.count{|a, b| a!= b }
  end