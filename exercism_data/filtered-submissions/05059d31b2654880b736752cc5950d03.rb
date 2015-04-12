class Hamming
  def compute(strand1, strand2)
    shorter_strand = [strand1, strand2].min { |strand1,strand2| strand1.size <=> strand2.size }
    (0...(shorter_strand.length)).count { |i| strand1[i] != strand2[i] }
  end
end
