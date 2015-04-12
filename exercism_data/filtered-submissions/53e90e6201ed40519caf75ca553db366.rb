def compute(a, b)
    as = a.split("")
    bs = b.split("")
    as_and_bs = as.zip(bs)

    as_and_bs.reduce(0) { |sum, cpl|
      n = cpl.all? && cpl[0] != cpl[1] ? 1 : 0
      sum + n
    }
  end
end