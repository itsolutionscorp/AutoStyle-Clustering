class Hamming
  def self.compute(strand1, strand2)
    differences = 0
    limit = [strand1.length, strand2.length].min
    (0...limit).each do |index|
      nucleotide1 = strand1[index]
      nucleotide2 = strand2[index]
      differences += 1 if nucleotide2 != nucleotide1
    end
    differences
  end
end
