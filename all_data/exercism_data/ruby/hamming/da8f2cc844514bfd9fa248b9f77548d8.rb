class Hamming
  def self.compute(strand1, strand2)
    return 0 if strand1.empty? || strand2.empty?
    (strand1.chr != strand2.chr ? 1 : 0) + compute(strand1[1..-1], strand2[1..-1])
  end
end
