class Hamming

  def self.compute(strand1, strand2)
    min = [strand1.length, strand2.length].min
    (0...min).count do |i|
      strand1[i] != strand2[i]
    end
  end

end
