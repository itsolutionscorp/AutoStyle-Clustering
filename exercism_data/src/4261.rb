def compute(base, other)
    res = 0
    base.length.times do |i|
      res += 1 unless base[i] == other[i]
    end
    res
  end