class Hamming
  def compute(a, b)
    errors = 0
    [a.length, b.length].min.times do |n|
      errors += 1 unless a[n-1] == b[n-1]
    end
    errors
  end
end
