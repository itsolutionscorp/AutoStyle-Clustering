class Hamming
  
  def self.compute(seq_a, seq_b)
    zip_seq(seq_a, seq_b).count {|a,b| a != b}
  end
  
  def self.zip_seq(seq_a, seq_b)
    seq_a.chars.zip(seq_b.chars)
  end
  
end
