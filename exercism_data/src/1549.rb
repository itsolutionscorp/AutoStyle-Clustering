class Hamming

  def compute(a, b)
    sum = 0

    0.upto(a.length) do |x|
      next unless a[x] && b[x]

      sum += 1 if a[x] != b[x]
    end

    sum
  end

end
