class Hamming
  def compute(a, b)
    diff = 0
    for i in 0..([a.length, b.length].min - 1) do
      if a[i] != b[i] then
        diff = diff + 1
      end
    end
    diff
  end
end
