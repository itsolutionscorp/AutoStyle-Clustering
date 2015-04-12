class Hamming
  def compute(a, b)
    diffs = i = 0
    while i < a.length and i < b.length
      diffs += 1 if a[i] != b[i]
      i += 1
    end
    diffs
  end
end
