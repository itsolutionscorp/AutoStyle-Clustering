class Hamming
  def compute(a,b)
    raise ArgumentError "Strands must be of equal length" if a.length != b.length
    a.length.times.count {|index| a[index] != b[index]}
  end
end
