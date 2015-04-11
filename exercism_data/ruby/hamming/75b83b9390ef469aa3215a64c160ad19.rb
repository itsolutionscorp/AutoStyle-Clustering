class Hamming

  def self.compute(strand1, strand2)
    count = 0

    if strand1.length > strand2.length
      size = strand2.length
      strand1 = strand1[0..(size - 1)]

    elsif strand1.length < strand2.length
      size = strand1.length
      strand2 = strand2[0..(size - 1)]
    end

    strand1.length.times do |i|
      if strand1[i] != strand2[i]
        count += 1
      end
    end
    count
  end
end
