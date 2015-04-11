class Hamming
  def self.compute(strand_1, strand_2)
  strand_1, strand_2 = normalise(strand_1, strand_2)

    sum = 0
    strand_1.split('').each_with_index do |value, index|
      if strand_2[index] != value
        sum += 1
      end
    end
    sum
  end

  private
  def self.normalise(strand_1, strand_2)
    if strand_1.length > strand_2.length
      strand_1 = strand_1.slice(0, strand_2.length)
    elsif strand_2.length > strand_1.length
      strand_2 = strand_2.slice(0, strand_1.length)
    end

    return strand_1, strand_2
  end
end
