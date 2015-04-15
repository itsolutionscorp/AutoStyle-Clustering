class Hamming

  def self.compute(strand1, strand2)
    count = 0

    if strand1.length > strand2.length
      strand = strand2
    elsif strand1.length <= strand2.length
      strand = strand1
    end

    strand.length.times do |i|
      if strand1[i] != strand2[i]
        count += 1
      end
    end
    count
  end
end
