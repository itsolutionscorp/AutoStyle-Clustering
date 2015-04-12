class Hamming
  def compute(strand1, strand2)
    len1 = strand1.length()
    len2 = strand2.length()
    len = [len1, len2].min
    diff = 0
    for i in 0..len - 1
      if strand1[i] != strand2[i]
        diff = diff + 1
      end
    end
    diff
  end
end
