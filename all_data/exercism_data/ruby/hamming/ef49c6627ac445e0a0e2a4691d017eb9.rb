class Hamming
  def self.compute(strand1, strand2, differences=0)
    while strand1 != ""
      if strand1.slice!(0) != strand2.slice!(0)
        differences += 1
      end
        differences = Hamming.compute(strand1, strand2, differences)
    end
    differences
  end
end
