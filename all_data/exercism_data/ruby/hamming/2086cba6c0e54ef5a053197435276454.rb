class Hamming
  def self.compute(strand1, strand2, count=0)
    (0...[strand1.length, strand2.length].min).each do |i|
      output = strand1[i] <=> strand2[i]
      unless output == 0
        count += 1
      end
    end
    return count
  end
end
