class Hamming
  def self.compute(strand1, strand2)
    errors = 0
    strand1.length.times do |i|
      if (strand1[i] != strand2[i])
        errors += 1
      end
    end
    return errors
  end
end
