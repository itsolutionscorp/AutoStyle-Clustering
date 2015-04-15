class Hamming
  def self.compute(strand1, strand2)
    strand_char_arrays = [strand1.split(//), strand2.split(//)]
    longer_strand, shorter_strand = strand_char_arrays.max, strand_char_arrays.min
      if (longer_strand.length - shorter_strand.length) != 0
        longer_strand = longer_strand.take(shorter_strand.length)
      end
    longer_strand.zip(shorter_strand).map {|a,b| a.eql?(b)}.count(false)
  end
end
