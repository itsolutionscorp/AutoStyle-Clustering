class Hamming
  def compute(strand1, strand2)
    strand1 = strand1.split('')
    strand2 = strand2.split('')

    strand1.zip(strand2).
      select {|a,b| a && b && a != b}.
      size
  end
end
