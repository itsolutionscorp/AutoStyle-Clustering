class Hamming
  def compute(strand1, strand2)
    i = 0
    diff = 0
    while i < strand1.length
      diff += 1 if strand1[i] != strand2[i]
      i += 1
    end
    diff
  end

end
