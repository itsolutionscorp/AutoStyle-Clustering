class Hamming
  def self.compute(strand1, strand2)
    count = 0
    i = 0
    loop do
      if strand1[i] != strand2[i]
        count += 1
      end
      i += 1
      break if i >= strand1.length || i >= strand2.length
    end
    return count
  end
end
