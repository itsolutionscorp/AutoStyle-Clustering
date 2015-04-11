class Hamming
  def self.compute(a_strand, b_strand)
    char_pairs = a_strand.chars.zip(b_strand.chars)
    char_pairs.select{|pair| has_difference?(pair) }.length
  end

  private
  def self.has_difference?(pair)
    pair.compact.uniq.length > 1
  end
end
