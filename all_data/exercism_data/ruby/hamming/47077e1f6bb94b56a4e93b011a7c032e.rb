class Hamming
  def self.compute(a_strand, b_strand)
    bytepairs = a_strand.bytes.zip(b_strand.bytes)
    bytepairs.select{|pair| has_difference?(pair) }.length
  end

  private
  def self.has_difference?(pair)
    pair.compact.uniq.length > 1
  end
end
