class Hamming

  def self.compute(a, b)
    #count the number of letters that are different.
    ret = 0
    for x in (0..a.length-1)
      ret += 1 unless a[x] == b[x]
    end
    ret
  end

end
