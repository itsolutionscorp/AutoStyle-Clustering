class Hamming
  def self.compute(strand1, strand2)
    s1 = strand1.length
    s2 = strand2.length
    
    return nil if s1 != s2

    count = 0

    s1.times do |i|
      if strand1[i] != strand2[i]
        count += 1
      end
    end

    return count
  end
end
