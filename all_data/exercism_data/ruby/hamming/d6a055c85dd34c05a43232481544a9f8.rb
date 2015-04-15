class Hamming

	def self.compute(strand1, strand2)
    differences = 0
    for i in 0...shortest(strand1, strand2)
      differences += 1 if strand1[i] != strand2[i]
    end
    differences
  end

  def self.shortest(strand1, strand2)
    return strand1.length if strand1.length < strand2.length
    strand2.length
  end

end
