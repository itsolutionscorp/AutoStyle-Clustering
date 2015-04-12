def compute(strand1, strand2)
   pairs = strand1.chars.zip(strand2.chars)
   pairs.reject! { |x| x[0] == x[1] }
   pairs.length
  end