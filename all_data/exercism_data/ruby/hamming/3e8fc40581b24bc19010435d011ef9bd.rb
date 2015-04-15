class Hamming
  
  def self.compute(strand1, strand2)
  partition = strand1.chars.zip(strand2.chars).partition {|(strand1, strand2)| strand1 != nil && strand2 != nil && strand1 != strand2}
  partition[0].length  
  end
  
end
