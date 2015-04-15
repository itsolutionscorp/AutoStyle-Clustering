class Hamming
  def self.compute( strand_a, strand_b )
    diff = 0
    count = 0
    a = strand_a.split('').map(&:to_s)
    b = strand_b.split('').map(&:to_s)
    if strand_a.length > strand_b.length
      max = strand_b.length
    else
      max = strand_a.length
    end
    while count < max
      if a[count].to_s != b[count].to_s
        diff += 1
      end
      count += 1
    end
    return diff
  end
end
