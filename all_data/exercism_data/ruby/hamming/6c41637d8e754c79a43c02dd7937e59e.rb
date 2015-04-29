class Hamming
  def self.compute(strand1, strand2)
    diff = 0

    0.upto([strand1.size, strand2.size].min - 1) do |index|
      diff += 1 if strand1[index] != strand2[index]
    end

    diff
  end
end
