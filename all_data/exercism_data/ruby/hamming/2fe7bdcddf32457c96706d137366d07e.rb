class Hamming
  def self.compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count do |segment1, segment2|
      segment1 != segment2
    end
  end
end
