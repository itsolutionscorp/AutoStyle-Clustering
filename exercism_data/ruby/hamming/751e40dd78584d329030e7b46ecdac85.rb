class Hamming
  def self.compute(strand_A,strand_B)
    strand_A.chars.zip(strand_B.chars).count do |eigen0, eigen1| 
      eigen0 != eigen1
    end
  end
end
