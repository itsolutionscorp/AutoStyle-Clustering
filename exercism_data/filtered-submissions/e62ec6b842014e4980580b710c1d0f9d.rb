class Hamming
  def compute(a,b)
    difference = 0

    length = [a.length, b.length].min - 1

    for i in 0..(length)
      difference += 1 if a[i] != b[i]
    end

    difference
  end
end
