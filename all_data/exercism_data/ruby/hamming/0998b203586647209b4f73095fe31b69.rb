class Hamming
  def self.compute(a, b)
    if a.length < b.length
      Hamming.diff(a, b)
    else
      Hamming.diff(b, a)
    end
  end

  private
  def self.diff(a, b)
    diff = 0
    for i in (0..(a.length - 1))
      diff = diff + 1 if a[i] != b[i]
    end
    return diff
  end
end
