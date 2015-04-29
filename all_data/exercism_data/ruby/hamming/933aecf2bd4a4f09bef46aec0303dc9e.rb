class Hamming

  def self.compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    zipped_strands = strand1.zip(strand2)

    zipped_strands.count do |x,y|
      x != y
    end
  end
end
