class Hamming
  def self.compute(strand1, strand2)
    s1 = strand1.split("")
    s2 = strand2.split("")
    s1.zip(s2).reject {|pair| pair[0] == pair[1]}.count
  end
end
