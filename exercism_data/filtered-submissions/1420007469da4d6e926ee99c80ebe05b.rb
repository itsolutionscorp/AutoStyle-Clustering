class Hamming

  def compute(a,b)
    i = [a.length, b.length].min
    hamming = 0

    while i > 0 do
      i-=1
      hamming += 1 if a[i] != b[i]
    end

    hamming
  end

end
