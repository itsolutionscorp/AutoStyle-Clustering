class Hamming
  def self.compute(a, b)
    aa = a.split('')
    bb = b.split('')
    count = 0
    i = 0
    while i < a.length && i < b.length
      if aa[i] != bb[i]
        count += 1
      end
      i += 1
    end
    return count
  end
end
