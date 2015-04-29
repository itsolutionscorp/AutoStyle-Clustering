class Hamming

  def self.compute(dna1, dna2)
    # check if both string have same length
    return unless dna1.length == dna2.length
    # Create an array for each pair and check for conformity
    dna1.chars.zip(dna2.chars).count{|a, b| a != b}
  end

end
