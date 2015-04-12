class Hamming
  def compute(a, b)
    (0..a.length).reduce(0) { |difference,idx| a[idx] == b[idx] ? difference : difference + 1 }
  end
end
