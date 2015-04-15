class Hamming
  def self.compute(strand_1, strand_2)
    diff = 0

    strand_1.length.times do |i|
      next if strand_2[i].nil?
      diff += 1 if strand_1[i] != strand_2[i]
    end

    return diff
  end
end
