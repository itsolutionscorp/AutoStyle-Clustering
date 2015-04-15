class Hamming
  def self.compute(strand1, strand2)
    strands = [strand1, strand2]
    shorter_strand, longer_strand = strands.min, strands.max
    zipped = shorter_strand.chars.zip(longer_strand.chars)

    mutations_ary = zipped.map {|strand_chars| strand_chars.first == strand_chars.last ? 0 : 1}
    mutations_ary.inject(&:+)
  end
end
