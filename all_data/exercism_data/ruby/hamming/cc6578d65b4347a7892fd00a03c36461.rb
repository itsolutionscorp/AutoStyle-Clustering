class Hamming
  def self.compute(strand1, strand2)
    @hamming_distance = 0
    while strand1.length != 0 && strand2.length != 0
      @hamming_distance += 1 if strand1.slice!(0) != strand2.slice!(0)
    end
    @hamming_distance
  end
end
