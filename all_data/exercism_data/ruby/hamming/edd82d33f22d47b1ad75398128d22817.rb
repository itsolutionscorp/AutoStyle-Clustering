class Hamming

  def self.compute(strand1, strand2)
    range1 = 0..strand1.length
    range2 = 0..strand2.length

    if range1.last < range2.last
      total_range = 0...range1.last
    else
      total_range = 0...range2.last
    end

    hamming_count = 0

    total_range.count do |x|
      if strand1[x] != strand2[x]
        hamming_count += 1
      end
    end
  end

end
