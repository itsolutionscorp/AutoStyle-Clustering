class Hamming
  def compute(strand_a, strand_b)
    array = strand_a.chars.zip(strand_b.chars)
    array.select {|e| (e[0] != e[1]) && (e[1] != nil)}.size
  end
end
