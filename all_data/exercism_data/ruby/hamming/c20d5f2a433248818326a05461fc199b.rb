class Hamming
  def self.compute strand1, strand2
    strand1.chars.zip(strand2.chars).count do |c1, c2|
      c2 && c1 != c2
    end
  end
end
