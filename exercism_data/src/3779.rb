class Hamming
  def compute(strand1, strand2)
    strand1.split('').
      zip(strand2.split('')).
      select {|a,b| a && b && a != b}.
      size
  end
end
