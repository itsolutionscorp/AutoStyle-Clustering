class Hamming
  def self.compute(strand1, strand2)
    @hamming_distance = 0
    @strand1 = strand1.split("")
    @strand2 = strand2.split("")
    while @strand1.length != 0 && @strand2.length != 0
      acid_tester1 = @strand1.shift
      acid_tester2 = @strand2.shift
      @hamming_distance += 1 if acid_tester1 != acid_tester2
    end
    @hamming_distance
  end
end
