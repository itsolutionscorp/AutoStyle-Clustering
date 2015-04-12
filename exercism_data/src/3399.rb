class Hamming

  def compute(a,b)
    n = [a.length,b.length].min
    (0...n).map{ |i| a[i] != b[i] }.count(true)
  end

end
