class Hamming
  def compute(a, b)
    total = 0
    for x in 0..[a.length,b.length].min-1
      if a[x] != b[x]
        total = total + 1
      end
    end

    return total
  end
end
