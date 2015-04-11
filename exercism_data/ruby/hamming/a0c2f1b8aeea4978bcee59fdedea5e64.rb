class Hamming

  def self.compute(x, y)
    hamdist = 0
    if x.length <= y.length
      nuc_length = x.length
    else
      nuc_length = y.length
    end
    i = 0
    while i < nuc_length
      if x[i] == y[i]
        hamdist = hamdist + 0
      else
        hamdist = hamdist + 1
      end
      i += 1
    end
    hamdist
  end
end
