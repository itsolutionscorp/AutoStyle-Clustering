class Hamming
  def self.compute(dna1, dna2)
    dna1 = dna1.split(//)
    dna2 = dna2.split(//)
    arr = dna1.zip dna2
    arr.map {|a| a[0].eql?(a[1]) }.count{|c| c == false }
  end
end
